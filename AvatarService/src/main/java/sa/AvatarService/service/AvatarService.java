package sa.AvatarService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.AvatarService.repository.IAvatarRepository;
import sa.AvatarService.dto.ModelDTOMapper;
import sa.AvatarService.exception.ResourceNotFoundException;
import sa.AvatarService.domain.Avatar;
import sa.AvatarService.dto.AvatarDTO;

@Service
public class AvatarService {
    @Autowired
    private IAvatarRepository avatarRepository;
    @Autowired
    private ModelDTOMapper modelDTOMapper;

    public Avatar addAvatar(AvatarDTO avatarDTO){
        Avatar avatar= modelDTOMapper.avatarDTOtoAvatar(new Avatar(),avatarDTO);
        return avatarRepository.save(avatar);
    }
    public Avatar removeAvatar (String id){
        Avatar avatar =avatarRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Avatar","id",id));
        avatarRepository.deleteById(id);
        return avatar;
    }
    public Avatar updateAvatar(String id, AvatarDTO avatarDTO){
        Avatar avatar =avatarRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Avatar","id",id));
        Avatar updatedAvatar=modelDTOMapper.avatarDTOtoAvatar(avatar,avatarDTO);
        return avatarRepository.save(updatedAvatar);
    }
    public Avatar getAvatar(String id){
        return avatarRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Avatar","id",id));
    }


}
