package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.dto.PostByTutorDTO;
import helix.com.tutorapp.dto.PostByTutorDTO;
import helix.com.tutorapp.dto.PostByTutorDTO;
import helix.com.tutorapp.dto.TutorDTO;
import helix.com.tutorapp.model.entity.*;
import helix.com.tutorapp.model.entity.PostByTutor;
import helix.com.tutorapp.model.repository.PostTutorRepository;
import helix.com.tutorapp.model.repository.TutorRepository;
import helix.com.tutorapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helix on 11/1/2016.
 */
@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostTutorRepository postTutorRepository;

    public User findUserToken(String token) {
        return userRepository.findByToken(token);
    }

    public String getUsername(String token) {
        User user = userRepository.findByToken(token);
        return user.getUsername();
    }

    public TutorDTO updateTutor(String token, Long id, TutorDTO tutorDTO) {
        User user = userRepository.findByToken(token);
        Tutor tutor = tutorRepository.findById(id);
        TutorDTO tutorDTO1 = new TutorDTO();
        if (user.getTutor() == tutor) {
            tutor.setFristName(tutorDTO.getFristName());
            tutor.setLastName(tutorDTO.getLastName());
            tutor.setGender(tutorDTO.getGender());
            tutor.setLocation(tutorDTO.getLocation());
            tutor = tutorRepository.save(tutor);
            tutorDTO1.setLastName(tutor.getLastName());
            return tutorDTO;
        } else {
            throw new NullPointerException("Tutor khong ton tai");
        }
    }

    public PostByTutorDTO createPostTutor(String token, PostByTutorDTO tutorDTO) {
        User user = userRepository.findByToken(token);
        PostByTutor postByTutor = new PostByTutor();
        postByTutor.setTutor(user.getTutor());
        postByTutor.setTimePost(tutorDTO.getTimePost());
        postByTutor.setMoney(tutorDTO.getMoney());
        postByTutor.setPostContent(tutorDTO.getPostContent());
        postByTutor.setTimePost(tutorDTO.getTimePost());
        postByTutor.setTime(tutorDTO.getTime());
        postByTutor.setSubject(tutorDTO.getSubject());


        postByTutor = postTutorRepository.save(postByTutor);

        return tutorDTO;
    }


    public PostByTutorDTO editPostTutor(String token, Long id, PostByTutorDTO tutorDTO) {
        User user = userRepository.findByToken(token);
        PostByTutor postByTutor = postTutorRepository.findById(id);
        if (postByTutor.getTutor() == user.getTutor()) {
            postByTutor.setTimePost(tutorDTO.getTimePost());
            postByTutor.setMoney(tutorDTO.getMoney());
            postByTutor.setPostContent(tutorDTO.getPostContent());
            postByTutor.setTimePost(tutorDTO.getTimePost());
            postByTutor.setTime(tutorDTO.getTime());
            postByTutor.setSubject(tutorDTO.getSubject());
        }

        return tutorDTO;
    }

    public String deletePostTutor(String token, Long id) {
        User user = userRepository.findByToken(token);

        if (postTutorRepository.findById(id) != null) {
            PostByTutor postByTutor = postTutorRepository.findById(id);
            if (postByTutor.getTutor().equals(user.getTutor())) {
                postTutorRepository.delete(postByTutor);
                return "Delete Success";
            } else {
                return "Not Permission";
            }
        } else return "No Post this id";
    }

    public List<PostByTutorDTO> allPostTutor() {
        List<PostByTutor> postByTutorList = (List<PostByTutor>) postTutorRepository.findAll();
        List<PostByTutorDTO> postByTutorDTOs = new ArrayList<PostByTutorDTO>();
        for (PostByTutor postByTutor : postByTutorList) {
            PostByTutorDTO postByTutorDTO = new PostByTutorDTO();
            postByTutorDTO.setPostContent(postByTutor.getPostContent());
            postByTutorDTO.setTime(postByTutor.getTime());
            postByTutorDTO.setTimePost(postByTutor.getTimePost());
            postByTutorDTO.setMoney(postByTutor.getMoney());
            postByTutorDTO.setSubject(postByTutor.getSubject());
            postByTutorDTOs.add(postByTutorDTO);
        }
        return postByTutorDTOs;
    }


    public List<PostByTutorDTO> allPostByTutor(Long idTutor) {
        if (tutorRepository.findById(idTutor) != null) {
            List<PostByTutor> arrPost = postTutorRepository.findByTutorId(idTutor);
            List<PostByTutorDTO> postByTutorDTOs = new ArrayList<PostByTutorDTO>();
            for (PostByTutor postByTutor : arrPost) {
                PostByTutorDTO postByTutorDTO = new PostByTutorDTO();
                postByTutorDTO.setPostContent(postByTutor.getPostContent());
                postByTutorDTO.setTime(postByTutor.getTime());
                postByTutorDTO.setTimePost(postByTutor.getTimePost());
                postByTutorDTO.setMoney(postByTutor.getMoney());
                postByTutorDTO.setSubject(postByTutor.getSubject());
                postByTutorDTOs.add(postByTutorDTO);
            }
            return postByTutorDTOs;
        } else throw new NullPointerException("Tutor khong ton tai");
    }
}
