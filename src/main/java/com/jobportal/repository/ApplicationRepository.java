
package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.entity.Application;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    // Find applications by applicant email
    List<Application> findByApplicantEmail(String applicantEmail);

    // Find applications by job ID
    List<Application> findByJobId(Long jobId);

    // Check duplicate applications
    boolean existsByJobIdAndApplicantEmail(
            Long jobId, String applicantEmail);
}
