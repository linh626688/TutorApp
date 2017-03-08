package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.dto.RoleDTO;
import helix.com.tutorapp.dto.UserDTO;
import helix.com.tutorapp.model.entity.Parent;
import helix.com.tutorapp.model.entity.Role;
import helix.com.tutorapp.model.entity.Tutor;
import helix.com.tutorapp.model.entity.User;
import helix.com.tutorapp.model.repository.ParentRepository;
import helix.com.tutorapp.model.repository.TutorRepository;
import helix.com.tutorapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

/**
 * Created by helix on 10/18/2016.
 */
@Service
public class UserService {
    private static String UPLOADED_FOLDER = "D://temp//";

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

    public User setRoleUser(RoleDTO roleDTO, String token) {
        User user1 = userRepository.findByToken(token);
        if (user1 != null && user1.getRole() == null) {
            user1.setRole(roleDTO.getRole());
            if (roleDTO.getRole() == Role.TUTOR) {
                Tutor tutor = new Tutor();
                tutorRepository.save(tutor);
                user1.setTutor(tutor);
            } else if (roleDTO.getRole() == Role.PARENT) {
                Parent parent = new Parent();
                parentRepository.save(parent);
                user1.setParent(parent);
            }
            return userRepository.save(user1);
        } else {
            throw new NullPointerException("user khong hop le ");
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

    public String setAvatar(String token, MultipartFile multipartFile) {
        User user = userRepository.findByToken(token);
        if (multipartFile.isEmpty()) {
            return "Null";
        }
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(path);
            user.setAvatar(path.toString());
            System.out.println(user.getAvatar());
            userRepository.save(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";
    }
}
