package sa.ElementService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.ElementService.dto.ElementDTO;
import sa.ElementService.service.ElementService;
import sa.ElementService.domain.Element;

@RestController
@RequestMapping("/element")
public class ElementController {
    @Autowired
    private ElementService elementService;

    @PostMapping
    public ResponseEntity<Element> addElement(@RequestBody ElementDTO elementDTO){
        Element element = elementService.addElement(elementDTO);
        return new ResponseEntity<Element>(element, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Element> deleteElement(@PathVariable String id){
        Element element = elementService.removeElement(id);
        return new ResponseEntity<Element>(element, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Element> updateElement(@PathVariable String id, @RequestBody ElementDTO elementDTO){
        Element element = elementService.updateElement(id,elementDTO);
        return new ResponseEntity<Element>(element, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Element> getElement(@PathVariable String id){
        Element element = elementService.getElement(id);
        return new ResponseEntity<Element>(element, HttpStatus.OK);
    }
    @GetMapping("/")
    public String getIt(){
        return " element Service is working";
    }

}
