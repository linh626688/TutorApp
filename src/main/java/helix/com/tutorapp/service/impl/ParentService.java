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
        postByParent.setTimePost(postParentDTO.getTimePost());
        postByParent.setContact(postParentDTO.getContact());
        postByParent.setSalaryDesired(postParentDTO.getSalaryDesired());
        postByParent.setLocationDesired(postParentDTO.getLocationDesired());
        postByParent.setTimes(postParentDTO.getTimes());
        postByParent.setClassRequirement(postParentDTO.getClassRequirement());
        postByParent.setPeriod(postParentDTO.getPeriod());
        postByParent.setClassLevel(postParentDTO.getClassLevel());
        postByParent.setSubject(postParentDTO.getSubject());

        postByParent = postParentRepository.save(postByParent);

        return postParentDTO;
    }

    public PostByParentDTO editPostParent(String token, Long id, PostByParentDTO postParentDTO) {
        User user = userRepository.findByToken(token);
        PostByParent postByParent = postParentRepository.findById(id);
        if (postByParent.getParent() == user.getParent()) {
            postByParent.setTimePost(postParentDTO.getTimePost());
            postByParent.setContact(postParentDTO.getContact());
            postByParent.setSalaryDesired(postParentDTO.getSalaryDesired());
            postByParent.setLocationDesired(postParentDTO.getLocationDesired());
            postByParent.setTimes(postParentDTO.getTimes());
            postByParent.setClassRequirement(postParentDTO.getClassRequirement());
            postByParent.setPeriod(postParentDTO.getPeriod());
            postByParent.setClassLevel(postParentDTO.getClassLevel());
            postByParent.setSubject(postParentDTO.getSubject());


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


                postByParentDTO.setClassLevel(postByParent.getClassLevel());
            }
            return postByParentDTOs;
        } else throw new NullPointerException("Parent khong ton tai");

    }

    public String deletePostParent(String token, Long id) {
        User user = userRepository.findByToken(token);

        if (postParentRepository.findById(id) != null) {
            PostByParent postByParent = postParentRepository.findById(id);
            if (postByParent.getParent().equals(user.getParent())) {
                postParentRepository.delete(postByParent);
                return "Delete Success";
            } else {
                return "Not Permission";
            }
        } else return "No Post this id";

    }
}
