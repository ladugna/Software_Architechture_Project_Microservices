package sa.ElementService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sa.ElementService.dto.ElementType;

@Document
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Element {
    @Id
    private String id;
    private ElementType type;
    private double price;
}
