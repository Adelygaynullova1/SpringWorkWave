package semestrWork.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import semestrWork.dto.InvitationDto;
import semestrWork.dto.request.EmployerRegisterDto;
import semestrWork.exception.SaveException;
import semestrWork.model.*;
import semestrWork.security.UserDetailImpl;

import semestrWork.security.CheckForCompany;
import semestrWork.service.*;

import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    Logger log = LoggerFactory.getLogger(EmployerController.class);

    @Autowired
    private EmployerService employerService;

    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private WorkerService workerService;



    @Autowired
    private InvitationService invitationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String mainPage(Model model) {

        return "employermain";
    }

    @GetMapping("/registration")
    public String regPage(Model model) {
        log.debug("registration debug");
        log.warn("registration");
        return "employerregpage";
    }

    @RequestMapping("/registercheck")
    String register(@ModelAttribute("dto") EmployerRegisterDto dto, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (employerService.existsEmployerByUsername(dto.getUsername())) {
            model.addAttribute("error", "Такой пользователь уже есть");
            return "employerregpage";
        }

        String password = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Employer user = new Employer();
        user.setUserRole(UserRole.EMPLOYER);
        user.setUsername(dto.getUsername());
        user.setPassword(encodedPassword);
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPatronymic(dto.getPatronymic());
        user.setInn(dto.getInn());
        user.setCompanyName(dto.getCompanyName());


        CheckForCompany client = new CheckForCompany();
        Boolean answer =  client.checkInn(dto.getInn());
        System.out.println(answer);
        if (answer) {
            try {
                user = employerService.save(user);
            } catch (Exception e) {
                throw new SaveException("Ошибка при сохранении работодателя");
            }
            try {


                UserDetailImpl userDetail = new UserDetailImpl(user);
                System.out.println(userDetail.getUsername());
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetail, password);
                System.out.println(1234);
                Authentication authentication = authenticationManager.authenticate(authRequest);


                SecurityContextHolder.getContext().setAuthentication(authentication);
                SecurityContext securityContext = SecurityContextHolder.getContext();

                contextRepository.saveContext(securityContext, request, response);
                log.warn("context : " + SecurityContextHolder.getContext());
                return "redirect:/account";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", e.getMessage());
                return "employerregpage";
            }
        } else {
            model.addAttribute("error", "Инн не соответствует стандартам");
            return "employerregpage";
        }

    }

    @GetMapping("/openWorkerResume")
    public String openWorkerResumePage(HttpServletRequest request, Model model, Authentication authentication, @RequestParam("resume_id") Long resume_id) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl ) {
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();

            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.EMPLOYER.name()) ) {

                Resume resume = resumeService.findResumeById(resume_id);
                model.addAttribute("resumes", resume);

                String username = ((UserDetailImpl) authentication.getPrincipal()).getUsername();
                Employer employer = employerService.findEmployerByUsername(username);
                List<Vacancy> vacancyList = vacancyService.findVacancyByEmployerList(employer);
                model.addAttribute("vacancies" , vacancyList);
                String avatarPath =
                        ( resume.getAvatar() != null) ?
                                "/imgstore/" +
                                        resume.getAvatar().getPath() +
                                        resume.getAvatar().getFileName()
                                : "/static/static/img/avatar_default.svg";
                model.addAttribute("avatar", avatarPath);
                model.addAttribute("app_path", request.getContextPath());


            } else if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.WORKER.name()) ){
                return "login";
            }
            return "workerresumepage";
        } else {
            return "login";
        }

    }

    @PostMapping("/inviteOnVacancy")
    @ResponseBody
    public ResponseEntity<String> inviteOnVacancy(@RequestBody InvitationDto dto, Authentication authentication) {

            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();

            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.EMPLOYER.name()) ) {

                String username = ((UserDetailImpl) authentication.getPrincipal()).getUsername();
                Employer employer = employerService.findEmployerByUsername(username);
                Resume resume = resumeService.findResumeById(dto.getResume_id());
                Worker worker = workerService.findWorkerById(dto.getWorker_id());
                Vacancy vacancy = vacancyService.findVacancyById(dto.getVacancy_id());


                if (invitationService.existsByResumeIdAndVacancyIdAndEmployerId(resume.getId(),vacancy.getId(),employer.getId())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Вы уже предлагали на эту вакансию");

                } else {
                    Invitation invitation = new Invitation();
                    invitation.setEmployer(employer);
                    invitation.setWorker(worker);
                    invitation.setResume(resume);
                    invitation.setVacancy(vacancy);
                    invitationService.save(invitation);
                    return ResponseEntity.status(HttpStatus.OK).body("Все гуд");
                }
            } else  {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("У пользователя нет прав");
            }


    }

    @PostMapping("/findWorker")
    public String findWorker(Model model, @RequestParam(value = "resume_name", required = false) String profession) {
        Iterable<Resume> resumes = resumeService.findResumeByProfession(profession);
        model.addAttribute("resumes", resumes);
        model.addAttribute("professi", profession);

        String path = "/employer/openWorkerResume";

        model.addAttribute("action", path);
        return "searchforworker";
    }

    @PostMapping("/findWorkerNext")
    public String findWorkerNext(Model model, @RequestParam(value = "resume_name", required = false) String profession) {
        Iterable<Resume> resumes = resumeService.findResumeByProfession(profession);
        model.addAttribute("resumes", resumes);
        model.addAttribute("professi", profession);

        String path = "/employer/openWorkerResume";

        model.addAttribute("action", path);
        return "searchforworker";
    }




}
