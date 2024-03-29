package com.example.JobApplication.Controller;

import com.example.JobApplication.Dto.ApplyForJobDTO;
import com.example.JobApplication.Dto.CompanyDto;
import com.example.JobApplication.Dto.LoginDto;
import com.example.JobApplication.Dto.UserDto;
import com.example.JobApplication.Repository.ApplicantRepository;
import com.example.JobApplication.Repository.JobPostingRepository;
import com.example.JobApplication.Service.Implementation.ApplicantService;
import com.example.JobApplication.Service.Implementation.AuthenticateService;
import com.example.JobApplication.Service.Implementation.JobAppService;
import com.example.JobApplication.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/job")
public class HomeContrroller {

    @Autowired
    private JobAppService jobAppService;

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private AuthenticateService authenticateService;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    @PostMapping("/saveuser")
    public void saveUser(@RequestBody User user) {
        authenticateService.saveUser(user.getUsername(), user.getPassword());
    }

    @PostMapping("/login")
    public LoginDto loginUser(@RequestBody User userDto) throws IOException {
        return authenticateService.loginUser(userDto);
    }


    @PostMapping("/savecompany")
    public Company saveCompany(@RequestBody CompanyDto companyDto) throws IOException {
        return jobAppService.saveCompany(companyDto);
    }

    @PostMapping("/savejob/{employer_id}")
    public JobPosting saveJob(@PathVariable int employer_id, @RequestBody List<JobPosting> jobPostings) throws IOException {
        return jobAppService.saveJob(employer_id, jobPostings);
    }

    @PostMapping("/applyToJob/{jobposting_id}")
    public void applyForJob(@PathVariable int jobposting_id, @RequestBody List<Applicant> applicants) {
        applicantService.applyForJob(jobposting_id, applicants);
    }

    @PostMapping("/saveworkexperience/{applicantId}")
    public ResponseEntity<String> saveWorkExperience(@PathVariable int applicantId, @RequestBody WorkExperience workExperience) {

        try {
            applicantService.saveWorkExperience(applicantId, workExperience);
            return new ResponseEntity<>("Work Experience saved successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Applicant not found with ID: " + applicantId, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/saveeducation/{applicantId}")
    public ResponseEntity<String> saveEducation(@PathVariable int applicantId, @RequestBody List<Education> educationList) {

        try {
            applicantService.saveEducation(applicantId, educationList);
            return new ResponseEntity<>("Education saved successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Applicant not found with ID: " + applicantId, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/saveskills/{applicantId}")
    public ResponseEntity<String> saveSkills(@PathVariable int applicantId, @RequestBody List<Skill> skills) {

        try {
            applicantService.saveSkills(applicantId, skills);
            return new ResponseEntity<>("Skills saved successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Applicant not found with ID: " + applicantId, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/savecertification/{applicantId}")
    public ResponseEntity<String> saveCertification(@PathVariable int applicantId, @RequestBody List<Certification> certifications) {

        try {
            applicantService.saveCertification(applicantId, certifications);
            return new ResponseEntity<>("Certifications saved successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Applicant not found with ID: " + applicantId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editeducation/{applicantId}/{educationId}")
    public ResponseEntity<String> editEducation(
            @PathVariable int applicantId,
            @PathVariable int educationId,
            @RequestBody Education updatedEducation) {

        try {
            applicantService.editEducation(applicantId, educationId, updatedEducation);
            return new ResponseEntity<>("Education updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editcertification/{applicantId}/{certificationId}")
    public ResponseEntity<String> editCertification(
            @PathVariable int applicantId,
            @PathVariable int certificationId,
            @RequestBody Certification updatedCertification) {

        try {
            applicantService.editCertification(applicantId, certificationId, updatedCertification);
            return new ResponseEntity<>("Certification updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editworkexperience/{applicantId}/{workExperienceId}")
    public ResponseEntity<String> editWorkExperience(
            @PathVariable int applicantId,
            @PathVariable int workExperienceId,
            @RequestBody WorkExperience updatedWorkExperience) {

        try {
            applicantService.editWorkExperience(applicantId, workExperienceId, updatedWorkExperience);
            return new ResponseEntity<>("Work Experience updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editskill/{applicantId}/{skillId}")
    public ResponseEntity<String> editSkill(
            @PathVariable int applicantId,
            @PathVariable int skillId,
            @RequestBody Skill updatedSkill) {

        try {
            applicantService.editSkill(applicantId, skillId, updatedSkill);
            return new ResponseEntity<>("Skill updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
