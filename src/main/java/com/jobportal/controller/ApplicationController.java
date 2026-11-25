
package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entity.Application;
import com.jobportal.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply for a job
    @PostMapping("/apply")
    public Application applyForJob(@RequestBody Application application) {

        return applicationService.applyForJob(application);
    }

    // View applications submitted by a candidate
    @GetMapping("/applicant/{email}")
    public List<Application> getApplicationsByApplicant(
            @PathVariable String email) {

        return applicationService.getApplicationsByApplicant(email);
    }

    // View applications for a particular job
    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(
            @PathVariable Long jobId) {

        return applicationService.getApplicationsByJob(jobId);
    }

    // View all applications
    @GetMapping("/all")
    public List<Application> getAllApplications() {

        return applicationService.getAllApplications();
    }
}
