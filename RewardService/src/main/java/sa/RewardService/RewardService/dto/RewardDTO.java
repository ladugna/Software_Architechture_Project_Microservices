package sa.RewardService.RewardService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardDTO {
    private String id;
    private String name;
    private int quantity;
    private ERewardType type;
    private double price;
}


