package helix.com.tutorapp.controller;

import helix.com.tutorapp.api.googlemapresponse.GoogleMapResult;
import helix.com.tutorapp.dto.LocationDTO;
import helix.com.tutorapp.dto.ParentDTO;
import helix.com.tutorapp.dto.PostByParentDTO;
import helix.com.tutorapp.model.entity.PostByParent;
import helix.com.tutorapp.model.entity.PostByTutor;
import helix.com.tutorapp.service.impl.ParentService;
import helix.com.tutorapp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by helix on 11/1/2016.
 */
@RestController
public class ParentController {
    @Autowired
    private ParentService parentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/updateParent/{id}", method = RequestMethod.PUT)
    public ParentDTO updateParent(@PathVariable("id") Long id, @RequestBody ParentDTO parentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.updateParent(token, id, parentDTO);

    }

    // Create Post
    @RequestMapping(value = "/createPostParent", method = RequestMethod.POST)
    public PostByParentDTO createPostParent(@RequestBody PostByParentDTO parentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.createPostParent(token, parentDTO);
    }


    //Edit Post theo ID
    @RequestMapping(value = "/editPostParent/{id}", method = RequestMethod.PUT)
    public PostByParentDTO editPostParent(@PathVariable("id") Long id, @RequestBody PostByParentDTO parentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.editPostParent(token, id, parentDTO);
    }


    //delete Post theo ID
    @RequestMapping(value = "/deletePostParent/{id}", method = RequestMethod.DELETE)
    public String deletePostParent(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.deletePostParent(token, id);
    }

    //List all Post
    @RequestMapping(value = "/allPostParent", method = RequestMethod.GET)
    public List<PostByParentDTO> allPostParent() {
        return parentService.allPostParent();
    }

    //get post theo ID
    @RequestMapping(value = "/postParent/{id}", method = RequestMethod.GET)
    public PostByParentDTO getPostParent(@PathVariable("id") Long id) {
        return parentService.getPostParent(id);
    }

    //list all Post by Parent
    @RequestMapping(value = "/allPostByParent/{id_parent}", method = RequestMethod.GET)
    public List<PostByParentDTO> allPostByParent(@PathVariable("id_parent") Long idParent) {
        return parentService.allPostByParent(idParent);
    }

    @RequestMapping(value = "/parent/getLocation/", method = RequestMethod.POST)
    public GoogleMapResult getLocationParent(@RequestBody LocationDTO location) {
        return userService.getLatLng(location);
    }

    @RequestMapping(value = "/parent/getLocationTest/", method = RequestMethod.GET)
    public GoogleMapResult getLocationParentTest() {
        return parentService.getLatLngTest();
    }

    @RequestMapping(value = "/parent/addLocation", method = RequestMethod.POST)
    public List<PostByParent> addLocation() {
        return parentService.addLocation();
    }

    @RequestMapping(value = "parent/findTutor", method = RequestMethod.POST)
    public List<PostByTutor> findTutorwithDistance(@RequestBody LocationDTO locationDTO, @RequestParam("distance") float distance) {
        return parentService.findParentwithDistance(locationDTO, distance);
    }
}
