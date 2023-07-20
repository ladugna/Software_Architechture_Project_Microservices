package sa.AvatarBuilderService.AvatarBuilderService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RewardItem {
    private String name;
    private int value;
}
