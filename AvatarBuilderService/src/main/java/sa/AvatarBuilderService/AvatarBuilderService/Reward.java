package sa.AvatarBuilderService.AvatarBuilderService;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Reward {
    private String id;
    private String name;
    private int quantity;
    private ERewardType type;
    private double price;
}
