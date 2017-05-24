package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.api.googlemapresponse.GoogleMapResult;
import helix.com.tutorapp.constant.GoogleMapApi;
import helix.com.tutorapp.dto.*;
import helix.com.tutorapp.model.entity.*;
import helix.com.tutorapp.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by helix on 10/18/2016.
 */
@Service
public class UserService {
    private static String UPLOADED_FOLDER = "C://xampp//htdocs//spring//upload//";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private PostTutorRepository postTutorRepository;

    @Autowired
    private MesssageRepository messsageRepository;

    @Autowired
    private RestTemplate restTemplate;


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
//                TutorDTO tutorDTO = new TutorDTO();
//                tutorDTO.setId(tutor.getId());
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
            result.setId(user.getId());
            if (user.getTutor() != null) {
                TutorDTO tutorDTO = new TutorDTO();
                tutorDTO.setId(user.getTutor().getId());
                tutorDTO.setBirth(user.getTutor().getBirth());
                tutorDTO.setCurrentJob(user.getTutor().getCurrentJob());
                tutorDTO.setLocation(user.getTutor().getLocation());
                tutorDTO.setName((user.getTutor().getName()));

                result.setTutor(tutorDTO);
            }
            if (user.getParent() != null) {
                ParentDTO parentDTO = new ParentDTO();
                parentDTO.setId(user.getParent().getId());
                parentDTO.setLocation(user.getParent().getLocation());

                result.setParent(parentDTO);
            }
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
            userRepository.save(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";
    }

    public String setTestAvatar(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return "Null";
        }
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(path.getFileName());
            String linkImage = "http://35.185.156.51/spring/upload/" + path.getFileName();
            System.out.println(linkImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";
    }

    public GoogleMapResult getLatLng(LocationDTO location) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("location", location.getLocation());

        return restTemplate.getForObject(
                GoogleMapApi.QUERY_LAT_LNG,
                GoogleMapResult.class,
                params
        );
    }

    public float radian(float x) {
        return (float) (x * Math.PI / 180);
    }

    public float getDistance(LocationDTO p1, LocationDTO p2) {
        float R = 6378137;
        float dLat = radian((float) (p2.getLat() - p1.getLat()));
        float dLng = radian((float) (p2.getLng() - p1.getLng()));
        float a = (float) (Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(radian((float) p1.getLat())) * Math.cos(radian((float) p2.getLat())) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2));
        float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
        float d = R * c;
        return d;
    }

    public MesssageDTO sentMessage(MesssageDTO messsageDTO, Long postId) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM");
        Date date = new Date();
        PostByTutor postByTutor = postTutorRepository.findById(postId);
        Long tutorId = postByTutor.getTutor().getId();
        Tutor tutor = tutorRepository.findById(postByTutor.getTutor().getId());

//        TutorDTO tutorDTO = new TutorDTO();
//        tutorDTO.setBirth(postByTutor.getTutor().getBirth());
//        tutorDTO.setCurrentJob(postByTutor.getTutor().getBirth());
//        tutorDTO.setLocation(postByTutor.getTutor().getLocation());
//        tutorDTO.setName((postByTutor.getTutor().getBirth()));
//        tutorDTO.setId(postByTutor.getTutor().getId());
//
        if ((tutorId >= 24) && (tutorId <= 46)) {
            Messsage messsage = new Messsage();
//            messsage.setState(false);
            messsage.setContact(messsageDTO.getContact());
            messsage.setEmail(messsageDTO.getEmail());
            messsage.setDetailRequest(messsageDTO.getDetailRequest());
            messsage.setTutor(tutorRepository.findById((long) 48));
            messsage.setTimeSend(dateFormat.format(date));
            messsageRepository.save(messsage);
            MesssageDTO dto = new MesssageDTO();
            dto.setId(messsage.getId());
            dto.setContact(messsage.getContact());
            dto.setEmail(messsage.getEmail());
            dto.setDetailRequest(messsage.getDetailRequest());
//            dto.setState(messsage.getState());
            return dto;
        } else {
            Messsage messsage = new Messsage();
//            messsage.setState(false);
            messsage.setContact(messsageDTO.getContact());
            messsage.setEmail(messsageDTO.getEmail());
            messsage.setTimeSend(dateFormat.format(date));
            messsage.setDetailRequest(messsageDTO.getDetailRequest());
            messsage.setTutor(tutor);
            messsageRepository.save(messsage);

            MesssageDTO dto = new MesssageDTO();
            dto.setId(messsage.getId());
            dto.setContact(messsage.getContact());
            dto.setEmail(messsage.getEmail());
            dto.setDetailRequest(messsage.getDetailRequest());
//            dto.setState(messsage.getState());
            dto.setTimeSend(messsage.getTimeSend());
            return dto;
        }

    }


}
