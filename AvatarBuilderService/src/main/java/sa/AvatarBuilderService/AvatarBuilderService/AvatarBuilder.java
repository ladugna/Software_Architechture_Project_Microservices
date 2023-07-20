package sa.AvatarBuilderService.AvatarBuilderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Service
public class AvatarBuilder {

    @Autowired
    private ElementFeignClient elementFeignClient;
    @Autowired
    private StudentGetFeignClient studentGetFeignClient;
    @Autowired
    private ElementPostFeignClient elementPostFeignClient;
    @Autowired
    private StudentPutFeignClient studentPutFeignClient;
    @Autowired
    private ElementDeleteFeignClient elementDeleteFeignClient;
//    private List<Element> elements=new ArrayList<>();
//    private List<Element> elements=new ArrayList<>();



    //to be replaced with data coming from authentication

//    private KafkaTemplate<String, Avatar> avatarkafkaTemplate;
//    private KafkaTemplate<String, Element> elementkafkaTemplate;

    public Avatar addElement(String studentId,String elementId){
        Element element=elementFeignClient.getElement(elementId);
        Student student = studentGetFeignClient.getStudent(studentId);
        double score=student.getScore();

        List<Element> elements= student.getElements();

        boolean hasMatchingType = elements.stream()
                .anyMatch(el -> el.getType().equals(element.getType()));

//        student.setElements(student.getElements().add());
        System.out.println(elements);

        if(score>=element.getPrice()&&!hasMatchingType){
            elements.add(element);
//            System.out.println(student.getScore());

            student.setScore(student.getScore()-element.getPrice());
            student.setAvatar(setElementToAvatar(studentId,element,element));
            student.setElements(elements);
            System.out.println(elements);
            //to remove element from elementservicedb
//            elementDeleteFeignClient.deleteElement(elementId);
//            System.out.println(student.getScore());
        }
        //check this
        else if(hasMatchingType){
            removeElement(studentId,elementId);
            student.setAvatar(setElementToAvatar(studentId,element, null));
            student.setScore(student.getScore()+element.getPrice());
            elements.remove(element);
            student.setElements(elements);
        }

//        elementkafkaTemplate.send("elementDeductTopic",element);
        System.out.println("Get avatar");
        studentPutFeignClient.updateStudent(studentId,student);

        return student.getAvatar();
//        return studentGetFeignClient.getStudent(studentId).getAvatar();
    }
    public Avatar removeElement(String studentId,String elementId){
        Element element=elementFeignClient.getElement(elementId);
        Student student = studentGetFeignClient.getStudent(studentId);
//        double score=student.getScore();
        List<Element> elements= student.getElements();

//        elements.removeIf(el->el.getType().equals(element.getType()));
        elements.remove(element);
        System.out.println(elements+"!!!!!check");

        student.setAvatar(setElementToAvatar(studentId,element,null));
        System.out.println(student.getScore()+"this is the score before removing the old");

        student.setScore(student.getScore()+element.getPrice());

        System.out.println(student.getScore()+"this is the score after removing the old");

//        elementPostFeignClient.addElement(element);
        student.setElements(elements);
//        System.out.println(student.getElements());

        studentPutFeignClient.updateStudent(studentId,student);
        return student.getAvatar();

    }

    public Avatar changeElement(String studentId, String newElementID,String oldElementId){
        Element newElement=elementFeignClient.getElement(newElementID);
        Element oldElement=elementFeignClient.getElement(oldElementId);
        Student student = studentGetFeignClient.getStudent(studentId);
        List<Element> elements= student.getElements();

        double score=student.getScore();
        if(newElement.getPrice()-oldElement.getPrice()<=score){
            elements.removeIf(el->el.getType().equals(oldElement.getType()));

            removeElement(studentId,oldElementId);

            addElement(studentId,newElementID);
            student.setAvatar(setElementToAvatar(studentId,oldElement,newElement));
//

            student.setElements(elements);
            studentPutFeignClient.updateStudent(studentId,student);
        }
        elementPostFeignClient.addElement(oldElement);
        return student.getAvatar();
    }
    public Avatar setElementToAvatar(String studentId,Element element, Element newElement) {
        Student student = studentGetFeignClient.getStudent(studentId);
        Avatar avatar=student.getAvatar();
        avatar.setId(studentId);
        switch (element.getType()) {
            case HEAD -> avatar.setHead(newElement);
            case HAIR -> avatar.setHair(newElement);
            case EYE -> avatar.setEye(newElement);
            case EYEBROW -> avatar.setEyebrow(newElement);
            case NOSE -> avatar.setNose(newElement);
            case MOUTH -> avatar.setMouth(newElement);
            case EARS -> avatar.setEars(newElement);
            case BODY -> avatar.setBody(newElement);
            case HAT -> avatar.setHat(newElement);
            case TOP -> avatar.setTop(newElement);
            default -> {
            }

        }
        student.setAvatar(avatar);
        return avatar;
    }
    public Avatar addColorToAvatar(String studentId, AvatarColorDTO avatarColorDTO){
        Student student = studentGetFeignClient.getStudent(studentId);
        Avatar avatar=student.getAvatar();
        if(avatarColorDTO.getType().name().equalsIgnoreCase("top")){
            avatar.setTopColor(avatarColorDTO.getColor());
        }
        else avatar.setHatColor(avatarColorDTO.getColor());
        return avatar;
    }
    public void submitAvatar(String studentId){
        Student student = studentGetFeignClient.getStudent(studentId);
        Avatar avatar=student.getAvatar();
//        avatarkafkaTemplate.send("avatarSaveTopic",avatar);
    }

    @FeignClient(name = "Element-Service")
    interface ElementFeignClient{
        @GetMapping("/element/{id}")
        public Element getElement(@PathVariable String id);
    }
    @FeignClient(name="Element-Service")
    interface ElementPostFeignClient{
        @PostMapping("/element")
        public Element addElement(@RequestBody Element element);
    }
    @FeignClient(name = "Student-Service")
    interface StudentGetFeignClient{
        @GetMapping("/students/{id}")
        public Student getStudent(@PathVariable String id);
    }
    @FeignClient(name = "Student-Service")
    interface StudentPutFeignClient{
        @PutMapping("/students/{id}")
        public Student updateStudent(@PathVariable String id,@RequestBody Student student);
    }
    @FeignClient(name="Element-Service")
    interface ElementDeleteFeignClient{
        @DeleteMapping("/element/{id}")
        public Element deleteElement(@PathVariable String id);
    }
}
