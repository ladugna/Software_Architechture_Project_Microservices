package sa.AvatarService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sa.AvatarService.domain.Avatar;

public interface IAvatarRepository extends MongoRepository <Avatar, String>{
}
