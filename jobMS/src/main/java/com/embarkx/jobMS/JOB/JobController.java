package com.embarkx.jobMS.JOB;

import com.embarkx.jobMS.JOB.DTO.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> findall()
    {
        return ResponseEntity.ok(jobService.findall());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createjob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added succesfully", HttpStatus.OK);
    }

    @GetMapping("/jobs/{Id}")
    public ResponseEntity<JobDTO> getjobbyID(@PathVariable Long Id) {
        JobDTO jobDTO = jobService.getjobbyID(Id);
        if (jobDTO != null)
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{Id}")
    public ResponseEntity<String> deletejobbyID(@PathVariable Long Id) {
        boolean delete = jobService.deletejobbyID(Id);
        if (delete)
        {
            return new ResponseEntity<>("Job deleted sucessfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("jobs/{id}")
    public ResponseEntity<String> updatejob(@PathVariable Long id, @RequestBody Job updatedjob) {
        boolean update = jobService.updatejob(id, updatedjob);
        if (update)
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
