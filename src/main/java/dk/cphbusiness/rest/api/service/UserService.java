package dk.cphbusiness.rest.api.service;

import dk.cphbusiness.rest.api.model.User;

import java.util.List;

public interface UserService {
    List<User> findByRole(String role);

    User findByUserName(String userName);

    void saveUser(User user);

    void deleteUserByUserName(String userName);

    boolean exist(User user);
}
