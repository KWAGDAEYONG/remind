package remind.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remind.Model.Answer;

/**
 * Created by user on 2017-04-07.
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
