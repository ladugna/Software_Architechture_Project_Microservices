package sa.AvatarBuilderService.AvatarBuilderService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {



    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String school;
    private String teachingClassYear;
    private String teachingClassGroup;
    private double score;
    private Avatar avatar;//needs to change
    private List<Reward> rewards= new ArrayList<>();
    private List<Element> elements= new ArrayList<>();

//    private StudentClassDTO studentClassDTO;
//    private int score;
//    private String avatar;//needs to change
//    private List<RewardItem> rewards;

}
