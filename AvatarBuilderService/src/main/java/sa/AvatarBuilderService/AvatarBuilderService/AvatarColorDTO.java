package sa.AvatarBuilderService.AvatarBuilderService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarColorDTO {
    private ElementType type;
    private Color color;
}
