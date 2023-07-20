package sa.AvatarService.dto;

import org.springframework.stereotype.Component;
import sa.AvatarService.domain.Avatar;
import sa.AvatarService.dto.AvatarDTO;

@Component
public class ModelDTOMapper {
    public AvatarDTO avatarToAvatarDTO(AvatarDTO avatarDTO, Avatar avatar){
        avatarDTO.setId(avatarDTO.getId());
        avatarDTO.setHead(avatar.getHead());
        avatarDTO.setHair(avatar.getHair());
        avatarDTO.setEye(avatar.getEye());
        avatarDTO.setEyebrow(avatar.getEyebrow());
        avatarDTO.setNose(avatar.getNose());
        avatarDTO.setMouth(avatar.getMouth());
        avatarDTO.setEars(avatar.getEars());
        avatarDTO.setBody(avatar.getBody());
        avatarDTO.setHat(avatar.getHat());
        avatarDTO.setTop(avatar.getTop());
        avatarDTO.setTopColor(avatar.getTopColor());
        avatarDTO.setHatColor(avatar.getHatColor());
        return avatarDTO;
    }
    public Avatar avatarDTOtoAvatar(Avatar avatar, AvatarDTO avatarDTO){
        avatar.setId(avatarDTO.getId());
        avatar.setHead(avatarDTO.getHead());
        avatar.setHair(avatarDTO.getHair());
        avatar.setEye(avatarDTO.getEye());
        avatar.setEyebrow(avatarDTO.getEyebrow());
        avatar.setNose(avatarDTO.getNose());
        avatar.setMouth(avatarDTO.getMouth());
        avatar.setEars(avatarDTO.getEars());
        avatar.setBody(avatarDTO.getBody());
        avatar.setHat(avatarDTO.getHat());
        avatar.setTop(avatarDTO.getTop());
        avatar.setTopColor(avatarDTO.getTopColor());
        avatar.setHatColor(avatarDTO.getHatColor());
        return avatar;
    }
}
