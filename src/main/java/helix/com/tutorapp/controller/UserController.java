package helix.com.tutorapp.controller;

import helix.com.tutorapp.api.googlemapresponse.GoogleMapResult;
import helix.com.tutorapp.dto.LocationDTO;
import helix.com.tutorapp.dto.MesssageDTO;
import helix.com.tutorapp.dto.RoleDTO;
import helix.com.tutorapp.dto.UserDTO;
import helix.com.tutorapp.model.entity.Messsage;
import helix.com.tutorapp.model.entity.PostByTutor;
import helix.com.tutorapp.model.entity.Tutor;
import helix.com.tutorapp.model.entity.User;
import helix.com.tutorapp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by helix on 10/14/2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDTO checkLogin(@RequestBody UserDTO user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", user.getUsername());
        return userService.doLogin(user);
    }


    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public void addRole(HttpServletRequest request, @RequestBody RoleDTO role) {
        String token = request.getHeader("auth-token");
        userService.setRoleUser(role, token);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logOut(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        userService.doLogout(token);
    }

    @RequestMapping(value = "/upload", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {

        String token = request.getHeader("auth-token");
        return userService.setAvatar(token, multipartFile);
    }

    @RequestMapping(value = "/test/upload", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public String singleTestFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        return userService.setTestAvatar(multipartFile);
    }


    @RequestMapping(value = "/test/result", method = RequestMethod.POST)
    public GoogleMapResult googleMapResult(@RequestBody LocationDTO locationDTO) {
        return userService.getLatLng(locationDTO);
    }

    //sentMessage
    @RequestMapping(value = "/sentMessage/{tutorId}", method = RequestMethod.POST)
    public MesssageDTO sentMessage(@PathVariable("tutorId") Long id, @RequestBody MesssageDTO message) {
        return userService.sentMessage(message, id);
    }
}
