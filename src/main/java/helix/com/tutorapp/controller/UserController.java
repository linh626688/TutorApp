package helix.com.tutorapp.controller;

import helix.com.tutorapp.controller.dto.UserDTO;
import helix.com.tutorapp.model.User;
import helix.com.tutorapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by helix on 10/14/2016.
 */
@RestController
@SessionAttributes
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
        modelAndView.addObject("username", user.getUserName());
        return userService.doLogin(user);
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logOut(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        userService.doLogout(token);
    }
}
