package sa.RewardService.RewardService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sa.RewardService.RewardService.dto.ERewardType;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    @Id
    private String id;
    private String name;
    private int quantity;
    private ERewardType type;
    private double price;
}
