package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.dto.ParentDTO;
import helix.com.tutorapp.dto.PostByParentDTO;
import helix.com.tutorapp.model.entity.Parent;
import helix.com.tutorapp.model.entity.PostByParent;
import helix.com.tutorapp.model.entity.User;
import helix.com.tutorapp.model.repository.ParentRepository;
import helix.com.tutorapp.model.repository.PostParentRepository;
import helix.com.tutorapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        return postParentDTO;
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
        List<PostByParent> postByParentList = (List<PostByParent>) postParentRepository.findAll();
        List<PostByParentDTO> postByParentDTOs = new ArrayList<PostByParentDTO>();
        for (PostByParent postByParent : postByParentList) {
            PostByParentDTO postByParentDTO = new PostByParentDTO();
            postByParentDTO.setPostContent(postByParent.getPostContent());
            postByParentDTO.setTime(postByParent.getTime());
            postByParentDTO.setStatus(postByParent.getStatus());
            postByParentDTO.setGender(postByParent.getGender());
            postByParentDTO.setLevel(postByParent.getLevel());
            postByParentDTO.setSubject(postByParent.getSubject());
            postByParentDTOs.add(postByParentDTO);
        }
        return postByParentDTOs;
    }

    public List<PostByParentDTO> allPostByParent(Long id) {
        return null;
    }

    public String deletePostParent(String token, Long id) {


        return null;
    }
}
