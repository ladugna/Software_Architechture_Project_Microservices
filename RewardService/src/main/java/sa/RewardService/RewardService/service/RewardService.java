package sa.RewardService.RewardService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.RewardService.RewardService.dto.ModelDTOMapper;
import sa.RewardService.RewardService.exception.ResourceNotFoundException;
import sa.RewardService.RewardService.domain.Reward;
import sa.RewardService.RewardService.dto.RewardDTO;
import sa.RewardService.RewardService.repository.IRewardRepository;

@Service
public class RewardService {
    @Autowired
    private IRewardRepository rewardRepository;

    @Autowired
    private ModelDTOMapper modelDTOMapper;

    public Reward addReward(RewardDTO rewardDTO){
        Reward reward = modelDTOMapper.rewardDTOtoReward(new Reward(),rewardDTO);
        return rewardRepository.save(reward);
    }
    public Reward removeReward(String id){
        Reward reward = rewardRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Reward","id",id));
        rewardRepository.deleteById(id);
        return reward;
    }
    public Reward updateReward(String id, RewardDTO rewardDTO){
        Reward reward = rewardRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Reward","id",id));
        Reward updatedReward =modelDTOMapper.rewardDTOtoReward(reward,rewardDTO);
        return rewardRepository.save(reward);
    }

    public Reward getReward(String id){
//        System.out.println(rewardRepository.findById(id));
        return rewardRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Reward","id",id));
    }
}
