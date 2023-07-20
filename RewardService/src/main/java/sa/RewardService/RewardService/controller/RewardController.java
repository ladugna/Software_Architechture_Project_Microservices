package sa.RewardService.RewardService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.RewardService.RewardService.domain.Reward;
import sa.RewardService.RewardService.dto.RewardDTO;
import sa.RewardService.RewardService.service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @PostMapping("/re")
    public ResponseEntity<Reward> addReward(@RequestBody RewardDTO rewardDTO){
        Reward reward = rewardService.addReward(rewardDTO);
        return new ResponseEntity<Reward>(reward, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Reward> deleteReward(@PathVariable String id){
        Reward reward = rewardService.removeReward(id);
        return new ResponseEntity<Reward>(reward, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable String id,@RequestBody RewardDTO rewardDTO){
        Reward reward = rewardService.updateReward(id,rewardDTO);
        return new ResponseEntity<Reward>(reward, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reward> getReward(@PathVariable String id){
        Reward reward = rewardService.getReward(id);
        return new ResponseEntity<Reward>(reward, HttpStatus.OK);
    }
}
