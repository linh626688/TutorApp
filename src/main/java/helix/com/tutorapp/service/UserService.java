package helix.com.tutorapp.service;

import helix.com.tutorapp.controller.dto.UserDTO;
import helix.com.tutorapp.model.User;
import helix.com.tutorapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by helix on 10/18/2016.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        ///User user = new User();
        User user1 = userRepository.findByUserName(userDTO.getUserName());
        if (user1 == null) {
            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setPassWord(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            return userRepository.save(user);
        } else {
            throw new NullPointerException("username da ton tai!");
        }

    }

    public User findUserToken(String token) {
        return userRepository.findByToken(token);
    }

    public UserDTO doLogin(UserDTO userDTO) {

        User user = userRepository.findByUserName(userDTO.getUserName());

        if (userDTO.getPassword().equals(user.getPassWord())) {
            if (user.getToken() == null) {
                user.setToken(UUID.randomUUID().toString());
                user.setTokenExpiry(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
            } else {
                user.setTokenExpiry(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
            }
            user = userRepository.save(user);
            UserDTO result = new UserDTO();
            result.setUserName(user.getUserName());
            result.setRole(user.getRole());
            result.setToken(user.getToken());
            return result;
        } else {
            throw new NullPointerException("sai username hoac password");
        }
    }
    public void doLogout(String token) {
        User user = userRepository.findByToken(token);
        user.setToken(null);
        userRepository.save(user);
    }

}
