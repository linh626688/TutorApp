package helix.com.tutorapp.controller;

import helix.com.tutorapp.dto.*;
import helix.com.tutorapp.service.impl.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by DangThanhLinh on 20/03/2017.
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/db/addTutor", method = RequestMethod.POST)
    public String addTutor(@RequestBody List<TutorInputDTO> tutorDTOs) {
        return uploadService.addTutor(tutorDTOs);
    }

    @RequestMapping(value = "/db/addPaent", method = RequestMethod.POST)
    public String addPaent(@RequestBody List<ParentInputDTO> parentDTO) {
        return uploadService.addParent(parentDTO);
    }


}
