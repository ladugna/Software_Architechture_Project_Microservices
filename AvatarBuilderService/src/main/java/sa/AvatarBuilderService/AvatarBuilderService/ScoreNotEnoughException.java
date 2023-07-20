package sa.AvatarBuilderService.AvatarBuilderService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreNotEnoughException extends RuntimeException{
    String resourceName;
    String fieldName;
    String fieldValue;

    public ScoreNotEnoughException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s with %s :%s  not found",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
