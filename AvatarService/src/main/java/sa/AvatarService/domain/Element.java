package sa.AvatarService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import sa.AvatarService.dto.ElementType;
@Data
@AllArgsConstructor
public class Element {

    private String id;
    private ElementType type;
    private double price;
}