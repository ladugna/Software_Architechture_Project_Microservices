package sa.ElementService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ElementDTO {
    private String id;
    private ElementType type;
    private double price;

}
