package sa.AvatarService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.AvatarService.dto.AvatarDTO;
import sa.AvatarService.service.AvatarService;
import sa.AvatarService.repository.IAvatarRepository;
import sa.AvatarService.domain.Avatar;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    @Autowired
    private IAvatarRepository avatarRepository;
    @Autowired
    private AvatarService avatarService;

    @PostMapping
    public ResponseEntity<Avatar> addAvatar(@RequestBody AvatarDTO avatarDTO){
        Avatar avatar = avatarService.addAvatar(avatarDTO);
        return new ResponseEntity<Avatar>(avatar, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Avatar> deleteAvatar(@PathVariable String id){
        Avatar avatar = avatarService.removeAvatar(id);
        return new ResponseEntity<Avatar>(avatar, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Avatar> updateAvatar(@PathVariable String id,@RequestBody AvatarDTO avatarDTO){
        Avatar avatar = avatarService.updateAvatar(id,avatarDTO);
        return  new ResponseEntity<Avatar>(avatar,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Avatar> getAvatar(@PathVariable String id){
        Avatar avatar = avatarService.getAvatar(id);
        return new ResponseEntity<Avatar>(avatar, HttpStatus.OK);
    }
    @GetMapping
    public String test(){
        return "Avatar is working";
    }
}
