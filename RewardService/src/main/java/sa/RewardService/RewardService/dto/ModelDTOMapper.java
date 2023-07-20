package sa.RewardService.RewardService.dto;

import org.springframework.stereotype.Component;
import sa.RewardService.RewardService.domain.Reward;
import sa.RewardService.RewardService.dto.RewardDTO;

@Component
public class ModelDTOMapper {
    public RewardDTO rewardToRewardDTO(RewardDTO rewardDTO, Reward reward){
        rewardDTO.setId(reward.getId());
        rewardDTO.setType(reward.getType());
        rewardDTO.setName(reward.getName());
        rewardDTO.setQuantity(reward.getQuantity());
        rewardDTO.setPrice(reward.getPrice());
        return rewardDTO;
    }
    public Reward rewardDTOtoReward(Reward reward, RewardDTO rewardDTO){
        reward.setId(rewardDTO.getId());
        reward.setName(rewardDTO.getName());
        reward.setType(rewardDTO.getType());
        reward.setQuantity(rewardDTO.getQuantity());
        reward.setPrice(rewardDTO.getPrice());
        return reward;
    }
}
