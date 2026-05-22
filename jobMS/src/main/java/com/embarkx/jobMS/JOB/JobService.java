package com.embarkx.jobMS.JOB;

import com.embarkx.jobMS.JOB.DTO.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findall();
    void createJob(Job job);

    JobDTO getjobbyID(Long id);

    Boolean deletejobbyID(Long id);

    boolean updatejob(Long id, Job updatedjob);
}
