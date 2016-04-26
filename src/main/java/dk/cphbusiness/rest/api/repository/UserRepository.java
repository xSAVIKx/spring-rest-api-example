package dk.cphbusiness.rest.api.repository;

import dk.cphbusiness.rest.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByRoleIgnoreCase(String role);

    User findByUserName(String userName);
}
