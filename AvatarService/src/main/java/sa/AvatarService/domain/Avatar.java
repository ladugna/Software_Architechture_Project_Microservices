package sa.AvatarService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    @Id
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
