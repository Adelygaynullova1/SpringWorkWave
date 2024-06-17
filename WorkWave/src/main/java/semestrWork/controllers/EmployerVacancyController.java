package semestrWork.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import semestrWork.dto.request.VacancyDto;
import semestrWork.model.*;
import semestrWork.security.UserDetailImpl;
import semestrWork.security.UserDetailsServiseImpl;
import semestrWork.service.EmployerService;
import semestrWork.service.ResumeService;
import semestrWork.service.VacancyService;
import semestrWork.service.WorkerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/employer")
public class EmployerVacancyController {



    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private EmployerService employerService;


    private HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();





    @GetMapping("/vacancy")
    public String resumePage(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl) {
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.EMPLOYER.name()) ) {
                return "vacancy";
            } else {
                return "login";
            }
        } else {
            return "login";
        }
    }

    @GetMapping("/openVacancy")
    public String openVacancyPage( Model model, Authentication authentication, @RequestParam("vacancy_id") Long vacancy_id) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl) {
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();

            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.EMPLOYER.name()) ) {

                Vacancy vacancy = vacancyService.findVacancyById(vacancy_id);
                model.addAttribute("vacancy", vacancy);
            }
            return "vacancyPage";
        } else {
            return "employerregpage";
        }

    }



    @PostMapping("/saveVacancy")
    String saveVacancy(@ModelAttribute("dto") VacancyDto dto, Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl) {
            String username = ((UserDetailImpl) authentication.getPrincipal()).getUsername();

            try {

                Employer user = employerService.findEmployerByUsername(username);
                Vacancy vacancy = new Vacancy();
                vacancy.setEmployer(user);
                vacancy.setEducation(dto.getEducation());
                vacancy.setWorkExperience(dto.getWorkExperience());
                vacancy.setProfession(dto.getProfession());
                vacancy.setAboutVacancy(dto.getAboutVacancy());
                vacancy.setEmail(dto.getEmail());
                vacancy.setSchedule(dto.getSchedule());
                vacancy.setSkills(dto.getSkills());
                vacancy.setOffice(dto.getOffice());
                vacancyService.save(vacancy);

                return "redirect:/account";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", e.getMessage());
                return "vacancy";
            }

        }else {
                return "employerregpage";
            }

    }




}
