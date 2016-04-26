package dk.cphbusiness.rest.api.service;

import dk.cphbusiness.rest.api.model.User;
import dk.cphbusiness.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRoleIgnoreCase(role);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserByUserName(String userName) {
        userRepository.delete(userName);
    }

    @Override
    public boolean exist(User user) {
        return userRepository.exists(user.getUserName());
    }
}
