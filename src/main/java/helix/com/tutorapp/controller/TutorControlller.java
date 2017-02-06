package helix.com.tutorapp.controller;

import helix.com.tutorapp.dto.PostByTutorDTO;
import helix.com.tutorapp.dto.TutorDTO;
import helix.com.tutorapp.service.impl.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/deletePostTutor/{id}",method = RequestMethod.DELETE)
    public String deletePostTutor(@PathVariable("id") Long id,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return tutorService.deletePostTutor(token, id);
    }

}
