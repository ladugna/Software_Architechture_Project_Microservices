package sa.ElementService.dto;

import org.springframework.stereotype.Component;
import sa.ElementService.domain.Element;
import sa.ElementService.dto.ElementDTO;

@Component
public class ModelDTOMapper {
    public ElementDTO elementToAElementDTO(ElementDTO elementDTO, Element element){
        elementDTO.setId(element.getId());
        elementDTO.setType(element.getType());
        elementDTO.setPrice(element.getPrice());

        return elementDTO;
    }
    public Element elementDTOtoElement(Element element, ElementDTO elementDTO){
        element.setId(elementDTO.getId());
        element.setType(elementDTO.getType());
        element.setPrice(elementDTO.getPrice());
        return element;
    }
}
