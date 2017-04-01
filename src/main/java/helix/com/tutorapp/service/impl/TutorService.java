package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.api.googlemapresponse.GoogleMapResult;
import helix.com.tutorapp.dto.LocationDTO;
import helix.com.tutorapp.dto.PostByTutorDTO;
import helix.com.tutorapp.dto.TutorDTO;
import helix.com.tutorapp.model.entity.*;
import helix.com.tutorapp.model.entity.PostByTutor;
import helix.com.tutorapp.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helix on 11/1/2016.
 */
@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private PostParentRepository postParentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostTutorRepository postTutorRepository;
    @Autowired
    private UserService userService;
    private static String UPLOADED_FOLDER = "C://xampp//htdocs//spring//upload//";


    public User findUserToken(String token) {
        return userRepository.findByToken(token);
    }

    public String getUsername(String token) {
        User user = userRepository.findByToken(token);
        return user.getUsername();
    }

    public TutorDTO updateTutor(String token, Long id, TutorDTO tutorDTO) {
        User user = userRepository.findByToken(token);
        Tutor tutor = tutorRepository.findById(id);
        TutorDTO tutorDTO1 = new TutorDTO();
        if (user.getTutor() == tutor) {
            tutor.setName(tutorDTO.getName());
            tutor.setBirth(tutorDTO.getBirth());
            tutor.setCurrentJob(tutorDTO.getCurrentJob());
            tutor.setLocation(tutorDTO.getLocation());
            tutor = tutorRepository.save(tutor);

            return tutorDTO;
        } else {
            throw new NullPointerException("Tutor khong ton tai");
        }
    }

    public PostByTutorDTO createPostTutor(String token, PostByTutorDTO tutorDTO) {
        User user = userRepository.findByToken(token);
        Tutor tutor = tutorRepository.findById(user.getTutor().getId());
        PostByTutor postByTutor = new PostByTutor();
        postByTutor.setTutor(user.getTutor());
        postByTutor.setTimePost(tutorDTO.getTimePost());
        postByTutor.setArea(tutorDTO.getLocationDesired());
        postByTutor.setSalary(tutorDTO.getSalaryDesired());
        postByTutor.setAbout(tutorDTO.getAbout());
        postByTutor.setTimePost(tutorDTO.getTimePost());
        postByTutor.setTime(tutorDTO.getTimes());
        postByTutor.setLevelClass(tutorDTO.getClassLevel());
        postByTutor.setSubject(tutorDTO.getSubject());
        postByTutor.setTutor(tutorDTO.getTutor());
        postByTutor = postTutorRepository.save(postByTutor);

        tutorDTO.setId(postByTutor.getId());
        return tutorDTO;
    }


    public PostByTutorDTO editPostTutor(String token, Long id, PostByTutorDTO tutorDTO) {
        User user = userRepository.findByToken(token);
        PostByTutor postByTutor = postTutorRepository.findById(id);
        if (postByTutor.getTutor() == user.getTutor()) {
            postByTutor.setTimePost(tutorDTO.getTimePost());
            postByTutor.setArea(tutorDTO.getLocationDesired());
            postByTutor.setSalary(tutorDTO.getSalaryDesired());
            postByTutor.setAbout(tutorDTO.getAbout());
            postByTutor.setTimePost(tutorDTO.getTimePost());
            postByTutor.setTime(tutorDTO.getTimes());
            postByTutor.setLevelClass(tutorDTO.getClassLevel());
            postByTutor.setSubject(tutorDTO.getSubject());
        }

        return tutorDTO;
    }

    public String deletePostTutor(String token, Long id) {
        User user = userRepository.findByToken(token);

        if (postTutorRepository.findById(id) != null) {
            PostByTutor postByTutor = postTutorRepository.findById(id);
            if (postByTutor.getTutor().equals(user.getTutor())) {
                postTutorRepository.delete(postByTutor);
                return "Delete Success";
            } else {
                return "Not Permission";
            }
        } else return "No Post this id";
    }

    public List<PostByTutorDTO> allPostTutor() {
        List<PostByTutor> postByTutorList = (List<PostByTutor>) postTutorRepository.findAll();
        List<PostByTutorDTO> postByTutorDTOs = new ArrayList<PostByTutorDTO>();
        for (PostByTutor postByTutor : postByTutorList) {
            PostByTutorDTO postByTutorDTO = new PostByTutorDTO();
            postByTutorDTO.setTimes(postByTutor.getTime());
            postByTutorDTO.setLocationDesired(postByTutor.getArea());
            postByTutorDTO.setSalaryDesired(postByTutor.getSalary());
            postByTutorDTO.setAbout(postByTutor.getAbout());
            postByTutorDTO.setTimePost(postByTutor.getTimePost());
            postByTutorDTO.setSubject(postByTutor.getSubject());
            postByTutorDTO.setClassLevel(postByTutor.getLevelClass());
            postByTutorDTO.setImagePost(postByTutor.getImagePost());
            postByTutorDTO.setId(postByTutor.getId());
            postByTutorDTO.setImagePost(postByTutor.getImagePost());
            postByTutorDTO.setTutor(postByTutor.getTutor());
            postByTutorDTOs.add(postByTutorDTO);
        }
        return postByTutorDTOs;
    }


    public List<PostByTutorDTO> allPostByTutor(Long idTutor) {
        if (tutorRepository.findById(idTutor) != null) {
            List<PostByTutor> arrPost = postTutorRepository.findByTutorId(idTutor);
            List<PostByTutorDTO> postByTutorDTOs = new ArrayList<PostByTutorDTO>();
            for (PostByTutor postByTutor : arrPost) {
                PostByTutorDTO postByTutorDTO = new PostByTutorDTO();
                postByTutorDTO.setTimes(postByTutor.getTime());
                postByTutorDTO.setTimePost(postByTutor.getTimePost());
                postByTutorDTO.setLocationDesired(postByTutor.getArea());
                postByTutorDTO.setSalaryDesired(postByTutor.getSalary());
                postByTutorDTO.setAbout(postByTutor.getAbout());
                postByTutorDTO.setSubject(postByTutor.getSubject());
                postByTutorDTO.setClassLevel(postByTutor.getLevelClass());
                postByTutorDTO.setImagePost(postByTutor.getImagePost());
                postByTutorDTO.setId(postByTutor.getId());
                postByTutorDTO.setImagePost(postByTutor.getImagePost());


                postByTutorDTOs.add(postByTutorDTO);
            }
            return postByTutorDTOs;
        } else throw new NullPointerException("Tutor khong ton tai");
    }

    public String setImagePost(String token, String id, MultipartFile multipartFile) {
        User user = userRepository.findByToken(token);
        Tutor tutor = tutorRepository.findById(user.getTutor().getId());

        PostByTutor postByTutor = postTutorRepository.findByIdAndTutorId(Long.valueOf(id), tutor.getId());

        if (multipartFile.isEmpty()) {
            return "Null";
        }
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            String linkImage = "http://35.185.156.51/spring/upload/" + path.getFileName();
            postByTutor.setImagePost(linkImage);
            postTutorRepository.save(postByTutor);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";

    }

    public String addImage(MultipartFile multipartFile) {
        String linkImage = null;
        if (multipartFile.isEmpty()) {
            return "Null";
        }
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(path);
            linkImage = "http://35.185.156.51/spring/upload/" + path.getFileName();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkImage;

    }

    public String setImagePostNoToken(Long id, MultipartFile multipartFile) {
        PostByTutor postByTutor = postTutorRepository.findById(id);

        if (multipartFile.isEmpty()) {
            return "Null";
        }
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(path);
            String linkImage = "http://35.185.156.51/spring/upload/" + path.getFileName();
            postTutorRepository.save(postByTutor);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";

    }

    public PostByTutor getImage2(Long id) {
        PostByTutor postByTutor = postTutorRepository.findById(id);
        return postByTutor;
    }

    public PostByTutorDTO getPostTutor(Long id) {
        PostByTutor postByTutor = postTutorRepository.findById(id);
        PostByTutorDTO postByTutorDTO = new PostByTutorDTO();
        postByTutorDTO.setTimes(postByTutor.getTime());
        postByTutorDTO.setTimePost(postByTutor.getTimePost());
        postByTutorDTO.setLocationDesired(postByTutor.getArea());
        postByTutorDTO.setSalaryDesired(postByTutor.getSalary());
        postByTutorDTO.setAbout(postByTutor.getAbout());
        postByTutorDTO.setSubject(postByTutor.getSubject());
        postByTutorDTO.setClassLevel(postByTutor.getLevelClass());
        postByTutorDTO.setImagePost(postByTutor.getImagePost());
        postByTutorDTO.setId(postByTutor.getId());
        postByTutorDTO.setImagePost(postByTutor.getImagePost());
        return postByTutorDTO;

    }

    public List<PostByTutor> addLocation() {
        List<PostByTutor> tutors = (List<PostByTutor>) postTutorRepository.findAll();
        for (int i = 0; i < tutors.size(); i++) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocation(tutors.get(i).getArea());
            GoogleMapResult result = userService.getLatLng(locationDTO);
            float lat = result.getResult()[0].getGeometry().getLocation().getLat();
            float lng = result.getResult()[0].getGeometry().getLocation().getLng();
            tutors.get(i).setLat(lat);
            tutors.get(i).setLng(lng);

            postTutorRepository.save(tutors.get(i));
        }
        return tutors;
    }

    public List<PostByParent> findParentwithDistance(LocationDTO locationDTO, float distance) {
        List<PostByParent> postByParents = (List<PostByParent>) postParentRepository.findAll();
        List<PostByParent> result = new ArrayList<PostByParent>();
        for (int i = 0; i < postByParents.size(); i++) {
            LocationDTO locationDTO2 = new LocationDTO();
            locationDTO2.setLat(postByParents.get(i).getLat());
            locationDTO2.setLng(postByParents.get(i).getLng());

            if (userService.getDistance(locationDTO, locationDTO2) <= distance) {
                result.add(postByParents.get(i));
            }
        }
        return result;
    }


}

