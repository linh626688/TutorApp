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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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


    public List<Tutor> getAllTutor() {
        List<Tutor> tutors = tutorRepository.findAll();
        return tutors;
    }

    public PostByTutorDTO createPostTutor(String token, PostByTutorDTO tutorDTO) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
        Date date = new Date();
        User user = userRepository.findByToken(token);
        Tutor tutor = tutorRepository.findById(user.getTutor().getId());
        PostByTutor postByTutor = new PostByTutor();
        postByTutor.setTutor(user.getTutor());
        postByTutor.setTimePost(dateFormat.format(date));
        postByTutor.setArea(tutorDTO.getLocationDesired());
        postByTutor.setSalary(tutorDTO.getSalaryDesired());
        postByTutor.setAbout(tutorDTO.getAbout());
        postByTutor.setTimePost(tutorDTO.getTimePost());
        postByTutor.setTime(tutorDTO.getTimes());
        postByTutor.setLevelClass(tutorDTO.getClassLevel());
        postByTutor.setSubject(tutorDTO.getSubject());
        postByTutor.setTutor(tutor);
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation(tutorDTO.getLocationDesired());
        GoogleMapResult googleMapResult = userService.getLatLng(locationDTO);
        postByTutor.setLat(googleMapResult.getResults()[0].getGeometry().getLocation().getLat());
        postByTutor.setLng(googleMapResult.getResults()[0].getGeometry().getLocation().getLng());
        postByTutor = postTutorRepository.save(postByTutor);

        tutorDTO.setId(postByTutor.getId());
        return tutorDTO;
    }


    public PostByTutorDTO editPostTutor(String token, Long id, PostByTutorDTO tutorDTO) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
        Date date = new Date();
        User user = userRepository.findByToken(token);
        Tutor tutor = tutorRepository.findById(user.getTutor().getId());
        PostByTutor postByTutor = postTutorRepository.findById(id);
//        if (postByTutor.getTutor() == user.getTutor()) {

        postByTutor.setTimePost(tutorDTO.getTimePost());
        postByTutor.setArea(tutorDTO.getLocationDesired());
        postByTutor.setSalary(tutorDTO.getSalaryDesired());
        postByTutor.setAbout(tutorDTO.getAbout());
        postByTutor.setTimePost(tutorDTO.getTimePost());
        postByTutor.setTime(tutorDTO.getTimes());
        postByTutor.setTutor(tutor);
        postByTutor.setLevelClass(tutorDTO.getClassLevel());
        postByTutor.setSubject(tutorDTO.getSubject());
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation(tutorDTO.getLocationDesired());
        GoogleMapResult googleMapResult = userService.getLatLng(locationDTO);
        postByTutor.setLat(googleMapResult.getResults()[0].getGeometry().getLocation().getLat());
        postByTutor.setLng(googleMapResult.getResults()[0].getGeometry().getLocation().getLng());
        postByTutor = postTutorRepository.save(postByTutor);
        tutorDTO.setId(postByTutor.getId());

//        }

        return tutorDTO;
    }

    public String deletePostTutor(String token, Long id) {
        User user = userRepository.findByToken(token);

//        if (postTutorRepository.findById(id) != null) {
//            PostByTutor postByTutor = postTutorRepository.findById(id);
//            if (postByTutor.getTutor().equals(user.getTutor())) {
//                postTutorRepository.delete(postByTutor);
//                return "Delete Success";
//            } else {
//                return "Not Permission";
//            }
//        } else return "No Post this id";
        if (userRepository.findByToken(token) != null) {
            PostByTutor postByTutor = postTutorRepository.findById(id);
            postTutorRepository.delete(postByTutor);
            return "Delete Success";
        } else return "Not Permission";


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
                postByTutorDTO.setLat(postByTutor.getLat());
                postByTutorDTO.setLng(postByTutor.getLng());
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
        postByTutorDTO.setTutor(postByTutor.getTutor());
        postByTutorDTO.setLat(postByTutor.getLat());
        postByTutorDTO.setLng(postByTutor.getLng());
        return postByTutorDTO;

    }

    public List<PostByTutor> addLocation() {
        List<PostByTutor> tutors = (List<PostByTutor>) postTutorRepository.findAll();
        for (int i = 0; i < tutors.size(); i++) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocation(tutors.get(i).getArea());
            GoogleMapResult result = userService.getLatLng(locationDTO);
            float lat = result.getResults()[0].getGeometry().getLocation().getLat();
            float lng = result.getResults()[0].getGeometry().getLocation().getLng();
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

    public List<PostByParent> findParentwithDistanceNoLatLong(LocationDTO locationDTO, float distance) {
        List<PostByParent> postByParents = (List<PostByParent>) postParentRepository.findAll();
        List<PostByParent> result = new ArrayList<PostByParent>();
        GoogleMapResult latLng = userService.getLatLng(locationDTO);
        System.out.println(latLng.getResults().length);
        System.out.println(latLng.getResults()[0].formatted_address);

        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setLat(latLng.getResults()[0].getGeometry().getLocation().getLat());
        locationDTO1.setLng(latLng.getResults()[0].getGeometry().getLocation().getLng());
        locationDTO1.setLocation(latLng.getResults()[0].formatted_address);
        LocationDTO locationDTO2 = new LocationDTO();
        for (int i = 0; i < postByParents.size(); i++) {
            locationDTO2.setLat(postByParents.get(i).getLat());
            locationDTO2.setLng(postByParents.get(i).getLng());

            if (userService.getDistance(locationDTO1, locationDTO2) <= distance) {
                result.add(postByParents.get(i));
            }
        }
        return result;
    }


    public Tutor getTutor(Long id) {
        Tutor tutor = tutorRepository.findById(id);
        return tutor;
    }
}

