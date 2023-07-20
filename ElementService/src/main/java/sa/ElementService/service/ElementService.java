package sa.ElementService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.ElementService.repository.IElementRepository;
import sa.ElementService.dto.ModelDTOMapper;
import sa.ElementService.exception.ResourceNotFoundException;
import sa.ElementService.domain.Element;
import sa.ElementService.dto.ElementDTO;

@Service
public class ElementService {

    @Autowired
    private IElementRepository elementRepository;
    @Autowired
    private ModelDTOMapper modelDTOMapper;

    public Element addElement(ElementDTO elementDTO){
        Element element =modelDTOMapper.elementDTOtoElement(new Element(),elementDTO);
        return elementRepository.save(element);
    }
    public Element removeElement(String id){
        Element element=elementRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Element","id",id));
        elementRepository.deleteById(id);
        return element;
    }
    public Element updateElement(String id, ElementDTO elementDTO){
        Element element= elementRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Element","id",id));
        Element updatedElement = modelDTOMapper.elementDTOtoElement(element,elementDTO);
        return elementRepository.save(updatedElement);
    }
    public Element getElement(String id){
        return elementRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Element","id",id));
    }


}
