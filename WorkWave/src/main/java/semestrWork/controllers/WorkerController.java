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
import semestrWork.dto.FeedBackDto;
import semestrWork.dto.request.WorkerRegisterDto;
import semestrWork.exception.SaveException;
import semestrWork.model.*;
import semestrWork.security.UserDetailImpl;
import semestrWork.service.*;

import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    Logger log = LoggerFactory.getLogger(WorkerController.class);

    @Autowired
    private EmployerService employerService;


    @Autowired
    private WorkerService workerService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private VacancyService vacancyService;



    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/registration")
    public String regPage(Model model) {
        log.debug("registration debug");
        log.warn("registration");
        return "workerregpage";
    }

    @RequestMapping("/registercheck")
    String register(@ModelAttribute("dto") WorkerRegisterDto dto, HttpServletRequest request,  HttpServletResponse response, Model model) {
        if (workerService.existsWorkerByUsername(dto.getUsername())) {
            model.addAttribute("error", "Такой пользователь уже есть");
            return "workerregpage";
        }
        String password = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Worker user = new Worker();
        user.setUserRole(UserRole.WORKER);
        user.setUsername(dto.getUsername());
        user.setPassword(encodedPassword);
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPatronymic(dto.getPatronymic());

        try {

            try {
            user = workerService.save(user);
            } catch (Exception e) {
                throw new SaveException("Ошибка при сохранении работника");
            }

            UserDetailImpl userDetail = new UserDetailImpl(user);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetail, password);
            Authentication authentication = authenticationManager.authenticate(authRequest);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            SecurityContext securityContext = SecurityContextHolder.getContext();

            contextRepository.saveContext(securityContext, request, response);
            log.warn("context : " + SecurityContextHolder.getContext());
            return "redirect:/account";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "workerregpage";
        }
    }

    @PostMapping("/feedbackOnVacancy")
    @ResponseBody
    public ResponseEntity<String> feedbackOnVacancy(@RequestBody FeedBackDto dto, Authentication authentication) {

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();

        if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.WORKER.name()) ) {

            String username = ((UserDetailImpl) authentication.getPrincipal()).getUsername();
            Worker worker = workerService.findWorkerByUsername(username);
            FeedBack feedBack = new FeedBack();
            Resume resume = resumeService.findResumeById(dto.getResume_id());
            Employer employer = employerService.findEmployerById(dto.getEmployer_id());
            Vacancy vacancy = vacancyService.findVacancyById(dto.getVacancy_id());

            if (feedBackService.existsByResumeIdAndVacancyIdAndWorkerId(resume.getId(), vacancy.getId(), worker.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Вы уже откликались с этим резюме");

            } else {
                feedBack.setWorker(worker);
                feedBack.setEmployer(employer);
                feedBack.setResume(resume);
                feedBack.setVacancy(vacancy);
                feedBackService.save(feedBack);
                log.warn("feedback saving");
                return ResponseEntity.status(HttpStatus.OK).body("Все гуд");
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("У пользователя нет прав");
        }


    }

    @GetMapping("/openEmployerVacancy")
    public String openEmployerVacancy( Model model, Authentication authentication, @RequestParam("vacancy_id") Long vacancy_id) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl ) {
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();

            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.WORKER.name()) ) {

                Vacancy vacancy = vacancyService.findVacancyById(vacancy_id);
                model.addAttribute("vacancy", vacancy);

                String username = ((UserDetailImpl) authentication.getPrincipal()).getUsername();
                Worker worker = workerService.findWorkerByUsername(username);
                List<Resume> resumeList = resumeService.findResumeByWorkerList(worker);
                model.addAttribute("resumes" , resumeList);

            } else {
                return "login";
            }
            return "employervacancypage";
        } else {
            return "login";
        }

    }

    @PostMapping("/findVacancy")
    public String findVacancy(Model model, @RequestParam(value = "vacancy_name", required = false) String profession) {
        Iterable<Vacancy> vacancy = vacancyService.findVacancyByProfession(profession);
        model.addAttribute("vacancies", vacancy);
        model.addAttribute("professi", profession);

        String path = "/worker/openEmployerVacancy";

        model.addAttribute("action", path);
        return "searchforemployer";
    }

    @PostMapping("/findVacancyNext")
    public String findVacancyNext(Model model, @RequestParam(value = "vacancy_name", required = false) String profession) {
        Iterable<Vacancy> vacancy = vacancyService.findVacancyByProfession(profession);
        model.addAttribute("vacancies", vacancy);
        model.addAttribute("professi", profession);

        String path = "/worker/openEmployerVacancy";

        model.addAttribute("action", path);
        return "searchforemployer";
    }

}
