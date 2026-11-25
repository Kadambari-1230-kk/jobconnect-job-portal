
package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.Job;
import com.jobportal.repository.JobRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Post a new job
    public Job postJob(Job job) {

        job.setPostedDate(LocalDate.now());

        return jobRepository.save(job);
    }

    // Get all available jobs
    public List<Job> getAllJobs() {

        return jobRepository.findAll();
    }

    // Search jobs by title
    public List<Job> searchJobsByTitle(String title) {

        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    // Search jobs by location
    public List<Job> searchJobsByLocation(String location) {

        return jobRepository.findByLocationContainingIgnoreCase(location);
    }

    // Get jobs posted by employer
    public List<Job> getJobsByEmployer(String email) {

        return jobRepository.findByEmployerEmail(email);
    }

    // Get job by ID
    public Job getJobById(Long id) {

        return jobRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Job not found!"));
    }

    // Delete a job
    public void deleteJob(Long id) {

        jobRepository.deleteById(id);
    }
}
