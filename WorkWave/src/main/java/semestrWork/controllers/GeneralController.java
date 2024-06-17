package semestrWork.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import semestrWork.model.*;
import semestrWork.security.UserDetailImpl;
import semestrWork.service.*;

import java.util.List;

@Controller
public class GeneralController {

    Logger log = LoggerFactory.getLogger(GeneralController.class);

    @Autowired
    private WorkerService workerService;

    @Autowired
    private EmployerService employerService;
    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private ResumeService resumeService;



    private HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();





    @GetMapping("/login")
    public String loginPage(Model model) {
        log.info("login");
        return "login";
    }

    @GetMapping("/account")
    public String clientAccountPage(Model model , Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl) {
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
            String username = userDetails.getUsername();

            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.WORKER.name()) ) {
                System.out.println("hello");

                Worker worker = workerService.findWorkerByUsername(username);
                List<Resume> resumeList = resumeService.findResumeByWorkerList(worker);
                String path = "/worker/openResume";
                model.addAttribute("resumes", resumeList);
                model.addAttribute("worker",worker);
                model.addAttribute("action", path);
                List<Invitation> invitationList = invitationService.findInvitationByWorkerList(worker);
                model.addAttribute("invitations",invitationList);

                List<FeedBack> feedBackList = feedBackService.findFeedBackByWorkerList(worker);
                model.addAttribute("feedbacks",feedBackList);
            } else {
                Employer employer = employerService.findEmployerByUsername(username);
                List<Vacancy> vacancyList = vacancyService.findVacancyByEmployerList(employer);
                String path2 = "/employer/openVacancy";
                model.addAttribute("action", path2);
                model.addAttribute("employer", employer);
                model.addAttribute("vacancies", vacancyList);

                List<Invitation> invitationList = invitationService.findInvitationByEmployerList(employer);
                model.addAttribute("invitations",invitationList);

                List<FeedBack> feedBackList = feedBackService.findFeedBackByEmployerList(employer);
                model.addAttribute("feedbacks",feedBackList);
            }



            return "account";
        } else {
            return "login";
        }

    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();

        return "redirect:/login";
    }
}
