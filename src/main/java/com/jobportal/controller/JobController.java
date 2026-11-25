
package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entity.Job;
import com.jobportal.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobService jobService;

    // Post a new job
    @PostMapping("/post")
    public Job postJob(@RequestBody Job job) {
        return jobService.postJob(job);
    }

    // Get all jobs
    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Search jobs by title
    @GetMapping("/search/title")
    public List<Job> searchByTitle(@RequestParam String title) {
        return jobService.searchJobsByTitle(title);
    }

    // Search jobs by location
    @GetMapping("/search/location")
    public List<Job> searchByLocation(@RequestParam String location) {
        return jobService.searchJobsByLocation(location);
    }

    // Get employer's jobs
    @GetMapping("/employer/{email}")
    public List<Job> getJobsByEmployer(@PathVariable String email) {
        return jobService.getJobsByEmployer(email);
    }

    // Get job by ID
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // Delete job
    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully!";
    }
}
