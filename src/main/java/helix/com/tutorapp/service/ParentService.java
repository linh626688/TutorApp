package helix.com.tutorapp.service;

import helix.com.tutorapp.controller.dto.ParentDTO;
import helix.com.tutorapp.controller.dto.PostByParentDTO;
import helix.com.tutorapp.controller.dto.TutorDTO;
import helix.com.tutorapp.model.Parent;
import helix.com.tutorapp.model.PostByParent;
import helix.com.tutorapp.model.Tutor;
import helix.com.tutorapp.model.User;
import helix.com.tutorapp.repository.ParentRepository;
import helix.com.tutorapp.repository.PostParentRepository;
import helix.com.tutorapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by helix on 11/1/2016.
 */
@Service
public class ParentService {
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
            parent.setAva(parentDTO.getAva());
            parent.setGender(parentDTO.getGender());
            parent.setLocation(parentDTO.getLocation());
            parent = parentRepository.save(parent);
//            parentDTO1.setLastName(tutor.getLastName());
            return parentDTO;
        } else {
            throw new NullPointerException("Parent khong ton tai");
        }
    }

    public PostByParentDTO createPostParent(String token, PostByParentDTO postParentDTO) {

        User user = userRepository.findByToken(token);
        PostByParent postByParent = new PostByParent();
        postByParent.setParent(user.getParent());
        postByParent.setLevel(postParentDTO.getLevel());
        postByParent.setGender(postParentDTO.getGender());
        postByParent.setPostContent(postParentDTO.getPostContent());
        postByParent.setTimePost(postParentDTO.getTimePost());
        postByParent.setTime(postParentDTO.getTime());
        postByParent.setSubject(postParentDTO.getSubject());
        postByParent.setStatus(postParentDTO.getStatus());

        postByParent = postParentRepository.save(postByParent);

        return postParentDTO;
    }

    public PostByParentDTO editPostParent(String token, Long id, PostByParentDTO postParentDTO) {
//        User user = userRepository.findByToken(token);
//        ClassRoom classRoom = classRoomRepository.findById(id);
//        if (classRoom.getUser() == user) {
//            classRoom.setClassName(classroomDTO.getClassName());
//            classRoomRepository.save(classRoom);
//            return "Update Done";
//        } else throw new NullPointerException("No Permission With Class");

        User user = userRepository.findByToken(token);
        PostByParent postByParent = postParentRepository.findById(id);
        if (postByParent.getParent() == user.getParent()) {
            postByParent.setPostContent(postParentDTO.getPostContent());
            postByParent.setTime(postParentDTO.getTime());
            postByParent.setStatus(postParentDTO.getStatus());
            postByParent.setGender(postParentDTO.getGender());
            postByParent.setLevel(postParentDTO.getLevel());
            postByParent.setSubject(postParentDTO.getSubject());
        }

        return null;
    }
//
//    public String deletePost(String token, Long id) {
//        User user = userRepository.findByToken(token);
//        PostByParent postByParent = postParentRepository.findById(id);
//        if (postByParent.getParent().equals(user)) {
////            postParentRepository.delete(classRoom);
//            return "Delete Success";
//        } else return "Not Permission";
//    }
//
//}

    public List<PostByParentDTO> allPostParent() {
        return null;
    }

    public List<PostByParentDTO> allPostByParent(Long id) {
        return null;
    }

    public String deletePostParent(String token, Long id) {
        return null;
    }
}
