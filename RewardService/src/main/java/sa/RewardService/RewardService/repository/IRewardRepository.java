package sa.RewardService.RewardService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sa.RewardService.RewardService.domain.Reward;

public interface IRewardRepository extends MongoRepository<Reward, String> {
}
