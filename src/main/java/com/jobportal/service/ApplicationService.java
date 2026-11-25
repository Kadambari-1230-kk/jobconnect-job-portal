
package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.Application;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    // Apply for a job
    public Application applyForJob(Application application) {

        // Check whether job exists
        if (!jobRepository.existsById(application.getJobId())) {
            throw new RuntimeException("Job not found!");
        }

        // Prevent duplicate applications
        if (applicationRepository.existsByJobIdAndApplicantEmail(
                application.getJobId(),
                application.getApplicantEmail())) {

            throw new RuntimeException(
                    "You have already applied for this job!");
        }

        application.setStatus("PENDING");
        application.setAppliedDate(LocalDate.now());

        return applicationRepository.save(application);
    }

    // Get applications submitted by a candidate
    public List<Application> getApplicationsByApplicant(String email) {

        return applicationRepository.findByApplicantEmail(email);
    }

    // Get applications for a particular job
    public List<Application> getApplicationsByJob(Long jobId) {

        return applicationRepository.findByJobId(jobId);
    }

    // Get all applications
    public List<Application> getAllApplications() {

        return applicationRepository.findAll();
    }
}
