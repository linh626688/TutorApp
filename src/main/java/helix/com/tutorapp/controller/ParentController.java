package helix.com.tutorapp.controller;

import helix.com.tutorapp.controller.dto.ParentDTO;
import helix.com.tutorapp.controller.dto.PostByParentDTO;
import helix.com.tutorapp.controller.dto.TutorDTO;
import helix.com.tutorapp.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by helix on 11/1/2016.
 */
@RestController
@SessionAttributes
public class ParentController {
    @Autowired
    private ParentService parentService;

    @RequestMapping(value = "/updateParent/{id}", method = RequestMethod.PUT)
    public ParentDTO updateParent(@PathVariable("id") Long id, @RequestBody ParentDTO parentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.updateParent(token, id, parentDTO);

    }

    // Create Post
    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public PostByParentDTO createPost(@RequestBody PostByParentDTO parentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.createPost(token, parentDTO);
    }


    //Edit Post theo ID
    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.PUT)
    public PostByParentDTO editPost(@PathVariable("id") Long id, @RequestBody PostByParentDTO parentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.editPost(token, id, parentDTO);
    }


    //delete Post theo ID
    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return parentService.deletePost(token, id);
    }

    //List all Post
    @RequestMapping(value = "/allPostParent",method = RequestMethod.GET)
    public List<PostByParentDTO> allPostParent(){
        return parentService.allPostParent();
    }


    //list all Post by Parent
    @RequestMapping(value = "/allPostByParent/{id}",method = RequestMethod.GET)
    public List<PostByParentDTO> allPostByParent(@PathVariable("id") Long id){
        return parentService.allPostByParent(id);
    }
}
