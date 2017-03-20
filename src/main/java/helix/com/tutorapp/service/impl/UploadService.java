package helix.com.tutorapp.service.impl;

import helix.com.tutorapp.dto.*;
import helix.com.tutorapp.model.entity.Parent;
import helix.com.tutorapp.model.entity.PostByParent;
import helix.com.tutorapp.model.entity.PostByTutor;
import helix.com.tutorapp.model.entity.Tutor;
import helix.com.tutorapp.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static helix.com.tutorapp.model.entity.Role.PARENT;
import static helix.com.tutorapp.model.entity.Role.TUTOR;

/**
 * Created by DangThanhLinh on 20/03/2017.
 */
@Service
public class UploadService {

    private static final String DONE = "DONE";
    private static final String ERROR = "ERROR";

    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private PostTutorRepository postTutorRepository;
    @Autowired
    private PostParentRepository postParentRepository;

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
    Date date = new Date();

    public String addTutor(List<TutorInputDTO> tutorDTOs) {
        for (int i = 0; i < tutorDTOs.size(); i++) {
            Tutor tutor = new Tutor();
            TutorDTO tutorDTO1 = new TutorDTO();
            tutor.setName(tutorDTOs.get(i).getName());
            tutor.setGender(tutorDTOs.get(i).getGender());
            tutor.setBirth(tutorDTOs.get(i).getBirth());
            tutor.setCurrentJob(tutorDTOs.get(i).getCurrentJob());
            tutor.setLocation(tutorDTOs.get(i).getLocation());
            tutor.setRole(TUTOR);
            tutor = tutorRepository.save(tutor);

            PostByTutor postByTutor = new PostByTutor();
            postByTutor.setTutor(tutor);
            postByTutor.setArea(tutorDTOs.get(i).getLocation());
            postByTutor.setSalary(tutorDTOs.get(i).getSalary());
            postByTutor.setAbout(tutorDTOs.get(i).getAbout());
            postByTutor.setTimePost(dateFormat.format(date));
            postByTutor.setTime("Thoa Thuan");
            postByTutor.setSubject(tutorDTOs.get(i).getSubject());
            postByTutor.setLevelClass(tutorDTOs.get(i).getLevelClass());

            postByTutor = postTutorRepository.save(postByTutor);

        }
        return DONE;
    }

    public String addParent(List<ParentInputDTO> parentDTOs) {
        for (int i = 0; i < parentDTOs.size(); i++) {
            Parent parent = new Parent();
            parent.setName("Parent");
            parent.setRole(PARENT);
            parent.setLocation(parentDTOs.get(i).getLocationDesired());

            parentRepository.save(parent);

            PostByParent postByParent = new PostByParent();
            postByParent.setParent(parent);
            postByParent.setTimePost(dateFormat.format(date));
            postByParent.setContact(parentDTOs.get(i).getContact());
            postByParent.setSalaryDesired(parentDTOs.get(i).getSalaryDesired());
            postByParent.setLocationDesired(parentDTOs.get(i).getLocationDesired());
            postByParent.setTimes(parentDTOs.get(i).getTimes());
            postByParent.setClassRequirement(parentDTOs.get(i).getClassRequirement());
            postByParent.setPeriod(parentDTOs.get(i).getPeriod());
            postByParent.setClassLevel(parentDTOs.get(i).getClassLevel());
            postByParent.setSubject(parentDTOs.get(i).getSubject());

            postParentRepository.save(postByParent);

        }
        return DONE;
    }
}
