package helix.com.tutorapp.controller;

import helix.com.tutorapp.controller.dto.PostByTutorDTO;
import helix.com.tutorapp.controller.dto.TutorDTO;
import helix.com.tutorapp.model.PostByTutor;
import helix.com.tutorapp.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by helix on 11/1/2016.
 */
@RestController
@SessionAttributes
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

    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public PostByTutorDTO createPost(@RequestBody PostByTutorDTO tutorDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.createPost(token, tutorDTO);
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.PUT)
    public PostByTutorDTO editPost(@PathVariable("id") Long id, @RequestBody PostByTutorDTO tutorDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return tutorService.editPost(token, id, tutorDTO);
    }

    @RequestMapping(value = "/deletePost/{id}",method = RequestMethod.DELETE)
    public String deletePost(@PathVariable("id") Long id,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return tutorService.deletePost(token, id);
    }

}
