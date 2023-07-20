package sa.ElementService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sa.ElementService.domain.Element;

public interface IElementRepository extends MongoRepository<Element, String> {

}
