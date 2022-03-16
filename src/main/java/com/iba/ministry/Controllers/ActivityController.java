package com.iba.ministry.Controllers;

import com.iba.ministry.Entities.Activity;
import com.iba.ministry.Entities.Ministry;
import com.iba.ministry.Repositories.ActivityRepository;
import com.iba.ministry.Repositories.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MinistryRepository ministryRepository;

    @GetMapping()
    public List<Activity> list() {
        return activityRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Activity> getById(@PathVariable(value = "id") Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);
        return ResponseEntity.ok().body(activity);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Activity> save(@Validated @RequestBody Activity input) {
        activityRepository.save(input);
        return ResponseEntity.ok().body(input);
    }

    @PutMapping("/{id}/ministry/{ministryID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Activity assignActivityToMinistry(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "ministryID") Long ministryID
    ){
        Ministry ministry = ministryRepository.findById(ministryID).orElse(null);
        Activity activity = activityRepository.findById(id).orElse(null);
        assert ministry != null;
        assert activity != null;
        activity.setMinistry(ministry);
        return activityRepository.save(activity);
    }
}
