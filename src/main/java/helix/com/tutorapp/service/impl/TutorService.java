package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.dto.PostByTutorDTO;
import helix.com.tutorapp.dto.TutorDTO;
import helix.com.tutorapp.model.entity.Tutor;
import helix.com.tutorapp.model.entity.User;
import helix.com.tutorapp.model.repository.TutorRepository;
import helix.com.tutorapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by helix on 11/1/2016.
 */
@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private UserRepository userRepository;

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
            tutor.setAva(tutorDTO.getAva());
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
        return null;
    }


    public PostByTutorDTO editPostTutor(String token, Long id, PostByTutorDTO tutorDTO) {
        return null;
    }

    public String deletePostTutor(String token, Long id) {
        return null;
    }
}
