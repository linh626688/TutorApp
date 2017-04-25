package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.api.FacebookUser;
import helix.com.tutorapp.api.googlemapresponse.GoogleMapResult;
import helix.com.tutorapp.api.googlemapresponse.Location;
import helix.com.tutorapp.constant.FacebookAPIConstant;
import helix.com.tutorapp.constant.GoogleMapApi;
import helix.com.tutorapp.dto.LocationDTO;
import helix.com.tutorapp.dto.ParentDTO;
import helix.com.tutorapp.dto.PostByParentDTO;
import helix.com.tutorapp.model.entity.Parent;
import helix.com.tutorapp.model.entity.PostByParent;
import helix.com.tutorapp.model.entity.PostByTutor;
import helix.com.tutorapp.model.entity.User;
import helix.com.tutorapp.model.repository.ParentRepository;
import helix.com.tutorapp.model.repository.PostParentRepository;
import helix.com.tutorapp.model.repository.PostTutorRepository;
import helix.com.tutorapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by helix on 11/1/2016.
 */
@Service
public class ParentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private PostTutorRepository postTutorRepository;

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostParentRepository postParentRepository;

    public ParentDTO updateParent(String token, Long id, ParentDTO parentDTO) {
        User user = userRepository.findByToken(token);
        Parent parent = parentRepository.findById(id);
        ParentDTO parentDTO1 = new ParentDTO();
        if (user.getParent() == parent) {
            parent.setLocation(parentDTO.getLocation());
            parent = parentRepository.save(parent);
            return parentDTO;
        } else {
            throw new NullPointerException("Parent khong ton tai");
        }
    }

    public PostByParentDTO createPostParent(String token, PostByParentDTO postParentDTO) {

        User user = userRepository.findByToken(token);
        PostByParent postByParent = new PostByParent();
        postByParent.setParent(user.getParent());
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
        Date date = new Date();
        postByParent.setTimePost(dateFormat.format(date));
        postByParent.setContact(postParentDTO.getContact());
        postByParent.setSalaryDesired(postParentDTO.getSalaryDesired());
        postByParent.setLocationDesired(postParentDTO.getLocationDesired());
        postByParent.setTimes(postParentDTO.getTimes());
        postByParent.setClassRequirement(postParentDTO.getClassRequirement());
        postByParent.setPeriod(postParentDTO.getPeriod());
        postByParent.setClassLevel(postParentDTO.getClassLevel());
        postByParent.setSubject(postParentDTO.getSubject());

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation(postParentDTO.getLocationDesired());
        GoogleMapResult googleMapResult = userService.getLatLng(locationDTO);
        postByParent.setLat(googleMapResult.getResults()[0].getGeometry().getLocation().getLat());
        postByParent.setLng(googleMapResult.getResults()[0].getGeometry().getLocation().getLng());

        postByParent = postParentRepository.save(postByParent);
        postParentDTO.setId(postByParent.getId());

        return postParentDTO;
    }

    public PostByParentDTO editPostParent(String token, Long id, PostByParentDTO postParentDTO) {
        User user = userRepository.findByToken(token);
        PostByParent postByParent = postParentRepository.findById(id);
        if (postByParent.getParent() == user.getParent()) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
            Date date = new Date();
            postByParent.setTimePost(dateFormat.format(date));
            postByParent.setContact(postParentDTO.getContact());
            postByParent.setSalaryDesired(postParentDTO.getSalaryDesired());
            postByParent.setLocationDesired(postParentDTO.getLocationDesired());
            postByParent.setTimes(postParentDTO.getTimes());
            postByParent.setClassRequirement(postParentDTO.getClassRequirement());
            postByParent.setPeriod(postParentDTO.getPeriod());
            postByParent.setClassLevel(postParentDTO.getClassLevel());
            postByParent.setSubject(postParentDTO.getSubject());
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocation(postParentDTO.getLocationDesired());
            GoogleMapResult googleMapResult = userService.getLatLng(locationDTO);
            postByParent.setLat(googleMapResult.getResults()[0].getGeometry().getLocation().getLat());
            postByParent.setLng(googleMapResult.getResults()[0].getGeometry().getLocation().getLng());
            postParentDTO.setId(postByParent.getId());

            postParentRepository.save(postByParent);

        }

        return postParentDTO;
    }


    public List<PostByParentDTO> allPostParent() {
        List<PostByParent> postByParentList = (List<PostByParent>) postParentRepository.findAll();
        List<PostByParentDTO> postByParentDTOs = new ArrayList<PostByParentDTO>();
        for (PostByParent postByParent : postByParentList) {
            PostByParentDTO postByParentDTO = new PostByParentDTO();
            postByParentDTO.setContact(postByParent.getContact());
            postByParentDTO.setSalaryDesired(postByParent.getSalaryDesired());
            postByParentDTO.setLocationDesired(postByParent.getLocationDesired());
            postByParentDTO.setTimes(postByParent.getTimes());
            postByParentDTO.setClassRequirement(postByParent.getClassRequirement());
            postByParentDTO.setPeriod(postByParent.getPeriod());
            postByParentDTO.setClassLevel(postByParent.getClassLevel());
            postByParentDTO.setSubject(postByParent.getSubject());
            postByParentDTO.setId(postByParent.getId());
            postByParentDTO.setTimePost(postByParent.getTimePost());


            postByParentDTOs.add(postByParentDTO);
        }
        return postByParentDTOs;
    }

    public List<PostByParentDTO> allPostByParent(Long idParent) {
        if (parentRepository.findById(idParent) != null) {
            List<PostByParent> arrPost = postParentRepository.findByParentId(idParent);
            List<PostByParentDTO> postByParentDTOs = new ArrayList<PostByParentDTO>();
            for (PostByParent postByParent : arrPost) {
                PostByParentDTO postByParentDTO = new PostByParentDTO();
                postByParentDTO.setContact(postByParent.getContact());
                postByParentDTO.setSalaryDesired(postByParent.getSalaryDesired());
                postByParentDTO.setLocationDesired(postByParent.getLocationDesired());
                postByParentDTO.setTimes(postByParent.getTimes());
                postByParentDTO.setClassRequirement(postByParent.getClassRequirement());
                postByParentDTO.setPeriod(postByParent.getPeriod());
                postByParentDTO.setSubject(postByParent.getSubject());
                postByParentDTO.setId(postByParent.getId());
                postByParentDTO.setClassLevel(postByParent.getClassLevel());
                postByParentDTO.setTimePost(postByParent.getTimePost());
            }
            return postByParentDTOs;
        } else throw new NullPointerException("Parent khong ton tai");

    }

    public String deletePostParent(String token, Long id) {
        User user = userRepository.findByToken(token);

//        if (postParentRepository.findById(id) != null) {
//            PostByParent postByParent = postParentRepository.findById(id);
//            if (postByParent.getParent().equals(user.getParent())) {
//                postParentRepository.delete(postByParent);
//                return "Delete Success";
//            } else {
//                return "Not Permission";
//            }
//        } else return "No Post this id";
        if (userRepository.findByToken(token) != null) {
            PostByParent postByParent = postParentRepository.findById(id);
            postParentRepository.delete(postByParent);
            return "Delete Success";
        } else{
            return "Not Permission";
        }
    }

    public PostByParentDTO getPostParent(Long id) {
        PostByParent postByParent = postParentRepository.findById(id);
        PostByParentDTO postByParentDTO = new PostByParentDTO();
        postByParentDTO.setContact(postByParent.getContact());
        postByParentDTO.setSalaryDesired(postByParent.getSalaryDesired());
        postByParentDTO.setLocationDesired(postByParent.getLocationDesired());
        postByParentDTO.setTimes(postByParent.getTimes());
        postByParentDTO.setClassRequirement(postByParent.getClassRequirement());
        postByParentDTO.setPeriod(postByParent.getPeriod());
        postByParentDTO.setSubject(postByParent.getSubject());
        postByParentDTO.setId(postByParent.getId());
        postByParentDTO.setClassLevel(postByParent.getClassLevel());

        return postByParentDTO;
    }

    public GoogleMapResult getLatLngTest() {
        return restTemplate.getForObject(
                GoogleMapApi.GOOGLE_MAP_TEST,
                GoogleMapResult.class
        );
    }

    public List<PostByParent> addLocation() {
        List<PostByParent> parents = (List<PostByParent>) postParentRepository.findAll();
        for (int i = 0; i < parents.size(); i++) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocation(parents.get(i).getLocationDesired());
            GoogleMapResult result = userService.getLatLng(locationDTO);
            float lat = result.getResults()[0].getGeometry().getLocation().getLat();
            float lng = result.getResults()[0].getGeometry().getLocation().getLng();
            parents.get(i).setLat(lat);
            parents.get(i).setLng(lng);

            postParentRepository.save(parents.get(i));
        }
        return parents;
    }

    public List<PostByTutor> findParentwithDistance(LocationDTO locationDTO, float distance) {
        List<PostByTutor> postByTutors = (List<PostByTutor>) postTutorRepository.findAll();
        List<PostByTutor> result = new ArrayList<PostByTutor>();
        for (int i = 0; i < postByTutors.size(); i++) {
            LocationDTO locationDTO2 = new LocationDTO();
            locationDTO2.setLat(postByTutors.get(i).getLat());
            locationDTO2.setLng(postByTutors.get(i).getLng());

            if (userService.getDistance(locationDTO, locationDTO2) <= distance) {
                result.add(postByTutors.get(i));
            }
        }
        return result;
    }

    public List<PostByTutor> findTutotrwithDistanceNoLatLong(LocationDTO locationDTO, float distance) {
        List<PostByTutor> postByTutors = (List<PostByTutor>) postTutorRepository.findAll();
        List<PostByTutor> result = new ArrayList<PostByTutor>();
        GoogleMapResult latLng = userService.getLatLng(locationDTO);

        System.out.println(latLng);
        System.out.println(latLng.getResults().length);
        System.out.println(latLng.getResults()[0]);

        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setLat(latLng.getResults()[0].getGeometry().getLocation().getLat());
        locationDTO1.setLng(latLng.getResults()[0].getGeometry().getLocation().getLng());
        LocationDTO locationDTO2 = new LocationDTO();
        for (int i = 0; i < postByTutors.size(); i++) {
            locationDTO2.setLat(postByTutors.get(i).getLat());
            locationDTO2.setLng(postByTutors.get(i).getLng());

            if (userService.getDistance(locationDTO1, locationDTO2) <= distance) {
                result.add(postByTutors.get(i));
            }
        }
        return result;
    }

    public List<Parent> getAllParent() {

        List<Parent> parents = (List<Parent>) parentRepository.findAll();
        return parents;
    }
}
