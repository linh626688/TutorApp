package helix.com.tutorapp.service;

import helix.com.tutorapp.controller.dto.UserDTO;
import helix.com.tutorapp.model.Parent;
import helix.com.tutorapp.model.Role;
import helix.com.tutorapp.model.Tutor;
import helix.com.tutorapp.model.User;
import helix.com.tutorapp.repository.ParentRepository;
import helix.com.tutorapp.repository.TutorRepository;
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

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ParentRepository parentRepository;


    public User createUser(UserDTO userDTO) {
        ///User user = new User();
        User user1 = userRepository.findByUsername(userDTO.getUsername());
        if (user1 == null) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            if (userDTO.getRole() == Role.TUTOR) {
                Tutor tutor = new Tutor();
                tutorRepository.save(tutor);
                user.setTutor(tutor);
            } else if (userDTO.getRole() == Role.PARENT) {
                Parent parent = new Parent();
                parentRepository.save(parent);
                user.setParent(parent);
            }
            return userRepository.save(user);
        } else {
            throw new NullPointerException("username da ton tai!");
        }

    }

    public User findUserToken(String token) {
        return userRepository.findByToken(token);
    }

    public UserDTO doLogin(UserDTO userDTO) {

        User user = userRepository.findByUsername(userDTO.getUsername());

        if (userDTO.getPassword().equals(user.getPassword())) {
            if (user.getToken() == null) {
                user.setToken(UUID.randomUUID().toString());
                user.setTokenExpiry(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
            } else {
                user.setTokenExpiry(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
            }
            user = userRepository.save(user);
            UserDTO result = new UserDTO();
            result.setUsername(user.getUsername());
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
