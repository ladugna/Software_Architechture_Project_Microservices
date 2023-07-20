package sa.AvatarService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import sa.AvatarService.domain.Color;
import sa.AvatarService.domain.Element;

@Data
@AllArgsConstructor
public class AvatarDTO {

    private String id;
    private Element head;
    private Element hair;
    private Element eye;
    private Element eyebrow;
    private Element nose;
    private Element mouth;
    private Element ears;
    private Element body;
    private Element hat;
    private Element top;
    private Color topColor;
    private Color hatColor;
}
