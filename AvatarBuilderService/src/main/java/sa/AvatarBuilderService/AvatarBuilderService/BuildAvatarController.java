package sa.AvatarBuilderService.AvatarBuilderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buildAvatar/{studentID}")
public class BuildAvatarController {

    @Autowired
    private AvatarBuilder avatarBuilder;
    @Autowired
    private AvatarPostFeignClient avatarPostFeignClient;
    @Autowired
    private StudentGetFeignClient studentGetFeignClient;
    @Autowired
    private AvatarPutFeignClient avatarPutFeignClient;

    @PostMapping("/{elementId}")
    public ResponseEntity<Avatar>addElement(@PathVariable String studentID,@PathVariable String elementId){
        Avatar avatar=avatarBuilder.addElement(studentID,elementId);
        avatarPostFeignClient.addAvatar(avatar);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Avatar> getStudentAvatar(@PathVariable String studentID){
        Avatar avatar= studentGetFeignClient.getStudent(studentID).getAvatar();
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }
    @DeleteMapping("/{elementId}")
    public ResponseEntity<Avatar> removeElement(@PathVariable String studentID, @PathVariable String elementId){
        Avatar avatar = avatarBuilder.removeElement(studentID,elementId);
        String avatarId=avatar.getId();
        avatarPutFeignClient.updateAvatar(avatarId,avatar);
        return new ResponseEntity<>(avatar,HttpStatus.OK);
    }

    @PutMapping("/{newElementId}/{oldElementId}")
    public ResponseEntity<Avatar> changeElement(@PathVariable String studentID,@PathVariable String newElementId,@PathVariable String oldElementId){
        Avatar avatar = avatarBuilder.changeElement(studentID,newElementId,oldElementId);
        String avatarId=avatar.getId();
        avatarPutFeignClient.updateAvatar(avatarId,avatar);
        return new ResponseEntity<>(avatar,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Avatar> addColorToAvatar(@PathVariable String studentID, @RequestBody AvatarColorDTO avatarColorDTO){
        Avatar avatar = avatarBuilder.addColorToAvatar(studentID,avatarColorDTO);
        String avatarId=avatar.getId();
        avatarPutFeignClient.updateAvatar(avatarId,avatar);
        return new ResponseEntity<>(avatar,HttpStatus.OK);
    }
    @PostMapping("/reward/{rewardId}")
    public ResponseEntity<Reward> buyReward(@PathVariable String studentID, @PathVariable String rewardId ){
        Reward reward = avatarBuilder.buyReward(studentID,rewardId);
        return new ResponseEntity<Reward>(reward,HttpStatus.OK);
    }

    @FeignClient(name = "Avatar-Service")
    interface AvatarPostFeignClient{
        @PostMapping("/avatar")
        public Avatar addAvatar(@RequestBody Avatar avatar);
    }
    @FeignClient(name = "Avatar-Service")
    interface AvatarPutFeignClient{
        @PutMapping("/avatar/{id}")
        public Avatar updateAvatar(@PathVariable String id, @RequestBody Avatar avatar);
    }
    @FeignClient(name = "Student-Service")
    interface StudentGetFeignClient{
        @GetMapping("/students/{id}")
        public Student getStudent(@PathVariable String id);
    }


}
