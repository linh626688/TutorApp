package helix.com.tutorapp.controller;

import helix.com.tutorapp.dto.*;
import helix.com.tutorapp.model.entity.PostByParent;
import helix.com.tutorapp.model.entity.PostByTutor;
import helix.com.tutorapp.model.entity.Tutor;
import helix.com.tutorapp.service.impl.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by helix on 11/1/2016.
 */
@RestController
public class TutorControlller {
    @Autowired
    private TutorService tutorService;

    @RequestMapping(value = "/helloTutor", method = RequestMethod.GET)
    public String hello() {
        return "Hello";
    }

//    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
//    public String getUsername(HttpServletRequest request) {
//        String token = request.getHeader("auth-token");
//        return tutorService.getUsername(token);
//    }

    @RequestMapping(value = "/updateTutor/{id}", method = RequestMethod.PUT)
    public TutorDTO updateTutor(@PathVariable("id") Long id, @RequestBody TutorDTO tutorDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.updateTutor(token, id, tutorDTO);

    }

    @RequestMapping(value = "/createPostTutor", method = RequestMethod.POST)
    public PostByTutorDTO createPostTutor(@RequestBody PostByTutorDTO tutorDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.createPostTutor(token, tutorDTO);
    }

    @RequestMapping(value = "/editPostTutor/{id}", method = RequestMethod.PUT)
    public PostByTutorDTO editPostTutor(@PathVariable("id") Long id, @RequestBody PostByTutorDTO tutorDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.editPostTutor(token, id, tutorDTO);
    }

    //delete Post theo ID
    @RequestMapping(value = "/deletePostTutor/{id}", method = RequestMethod.DELETE)
    public void deletePostTutor(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
         tutorService.deletePostTutor(token, id);
    }

    //List all Post
    @RequestMapping(value = "/allPostTutor", method = RequestMethod.GET)
    public List<PostByTutorDTO> allPostTutor() {
        return tutorService.allPostTutor();
    }


    //list all Post by Tutor
    @RequestMapping(value = "/allPostByTutor/{id_Tutor}", method = RequestMethod.GET)
    public List<PostByTutorDTO> allPostByTutor(@PathVariable("id_Tutor") Long idTutor) {
        return tutorService.allPostByTutor(idTutor);
    }

    @RequestMapping(value = "/postByTutor/update-image-post", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        String postId = (request.getHeader("postId"));

        return tutorService.setImagePost(token, postId, multipartFile);
    }

    @RequestMapping(value = "/postByTutor/{id}/update-image-post-notoken", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public String singleFileUploadNoToken(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id) {
        return tutorService.setImagePostNoToken(id, multipartFile);
    }

    @RequestMapping(value = "/get-image/{post_id}", method = RequestMethod.GET)
    public PostByTutor getImage(@PathVariable("post_id") Long id) {
        return tutorService.getImage2(id);
    }

    @RequestMapping(value = "/postTutor/{id}", method = RequestMethod.GET)
    public PostByTutorDTO getPostTutor(@PathVariable("id") Long id) {
        return tutorService.getPostTutor(id);
    }

    @RequestMapping(value = "/tutor/addLocation", method = RequestMethod.POST)
    public List<PostByTutor> addLocation() {
        return tutorService.addLocation();
    }

    @RequestMapping(value = "tutor/findParent", method = RequestMethod.POST)
    public List<PostByParentDTO> findParentwithDistance(@RequestBody LocationDTO locationDTO, @RequestParam("distance") float distance) {
        return tutorService.findParentwithDistance(locationDTO, distance);
    }

    @RequestMapping(value = "tutor/findParentNoLatLong", method = RequestMethod.POST)
    public List<PostByParentDTO> findParentwithDistanceNoLatLong(@RequestBody LocationDTO locationDTO, @RequestParam("distance") float distance) {
        return tutorService.findParentwithDistanceNoLatLong(locationDTO, distance);
    }

    @RequestMapping(value = "/getAllTutor", method = RequestMethod.GET)
    public List<Tutor> getAllTutor() {
        return tutorService.getAllTutor();
    }

    @RequestMapping(value = "/getTutor/{id}", method = RequestMethod.GET)
    public Tutor getAllTutor(@PathVariable("id") Long id) {
        return tutorService.getTutor(id);
    }

    //    @RequestMapping(value = "tutor/calculate", method = RequestMethod.POST)
//    public float getDistance(@RequestBody List<LocationDTO> locationDTO) {
//        return tutorService.getDistance(locationDTO);
//    }

    //get message of tutor
    @RequestMapping(value = "/getAllMessages", method = RequestMethod.GET)
    public List<MesssageDTO> getAllMesssages(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.getAllMessages(token);
    }

    //getMessageDetail
    @RequestMapping(value = "/getMessagesDetail/{id}", method = RequestMethod.GET)
    public MesssageDTO getMessageDetail(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.getMessageDetail(id, token);
    }

    //removeMessage
    @RequestMapping(value = "/removeMessage/{id}", method = RequestMethod.DELETE)
    public void removeMessage(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        tutorService.removeMessage(id, token);
    }

    //
    //setStateMessage
    @RequestMapping(value = "/updateStateMessage/{id}", method = RequestMethod.PUT)
    public MesssageDTO setStateMessage(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.setStateMessage(id, token);
    }
}

