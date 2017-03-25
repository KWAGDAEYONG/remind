package remind.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import remind.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
}
