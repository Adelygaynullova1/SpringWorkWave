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
import semestrWork.dto.request.ResumeDto;
import semestrWork.exception.ImageException;
import semestrWork.model.*;
import semestrWork.security.UserDetailImpl;
import semestrWork.security.UserDetailsServiseImpl;
import semestrWork.service.ResumeService;
import semestrWork.service.WorkerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/worker")
public class WorkerResumeController {

    Logger log = LoggerFactory.getLogger(WorkerResumeController.class);


    private String defaultImg = "<?xml version=\"1.0\" encoding=\"utf-8\"?><svg width=\"800px\" height=\"800px\" viewBox=\"0 0 16 16\" xmlns=\"http://www.w3.org/2000/svg\"><g color=\"#000000\" font-weight=\"400\" font-family=\"Ubuntu\" letter-spacing=\"0\" word-spacing=\"0\" white-space=\"normal\" fill=\"gray\"><path d=\"M8 2a2.84 2.84 0 0 0-1.12.221c-.345.141-.651.348-.906.615v.003l-.001.002c-.248.269-.44.592-.574.96-.137.367-.203.769-.203 1.2 0 .435.065.841.203 1.209.135.361.327.68.574.95l.001.002c.254.267.558.477.901.624v.003c.346.141.723.21 1.12.21.395 0 .77-.069 1.117-.21v-.002c.343-.147.644-.357.892-.625.255-.268.45-.59.586-.952.138-.368.204-.774.204-1.21h.01c0-.43-.065-.831-.203-1.198a2.771 2.771 0 0 0-.585-.963 2.5 2.5 0 0 0-.897-.618A2.835 2.835 0 0 0 7.999 2zM8.024 10.002c-2.317 0-3.561.213-4.486.91-.462.35-.767.825-.939 1.378-.172.553-.226.975-.228 1.71L8 14.002h5.629c-.001-.736-.052-1.159-.225-1.712-.172-.553-.477-1.027-.94-1.376-.923-.697-2.124-.912-4.44-.912z\" style=\"line-height:125%;-inkscape-font-specification:'Ubuntu, Normal';font-variant-ligatures:normal;font-variant-position:normal;font-variant-caps:normal;font-variant-numeric:normal;font-variant-alternates:normal;font-feature-settings:normal;text-indent:0;text-align:start;text-decoration-line:none;text-decoration-style:solid;text-decoration-color:#000000;text-transform:none;text-orientation:mixed;shape-padding:0;isolation:auto;mix-blend-mode:normal\"overflow=\"visible\"/></g></svg>";

    private String imgStorePath = "C:/Users/User1/Desktop/WorkW" ;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private WorkerService userService;


    private HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();



    @GetMapping
    public String mainPage(Model model) {
        return "workermain";
    }


    @GetMapping("/resume")
    public String resumePage(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl) {
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.WORKER.name()) ) {
                return "resume";
            } else {
                return "login";
            }
        } else {
            return "login";
        }
    }

    @GetMapping("/openResume")
    public String openResumePage(HttpServletRequest request, Model model, Authentication authentication, @RequestParam("resume_id") Long resume_id) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailImpl) {
            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();

            if (userDetails.getAuthorities().toArray()[0].toString().equals(UserRole.WORKER.name()) ) {
                System.out.println("hello");

               Resume resume = resumeService.findResumeById(resume_id);
                String path = "/worker/updateResume";
                model.addAttribute("resumes", resume);

                String avatarPath =
                        ( resume.getAvatar() != null) ?
                                "/imgstore/" +
                                        resume.getAvatar().getPath() +
                                        resume.getAvatar().getFileName()
                                : "/static/static/img/avatar_default.svg";
                model.addAttribute("avatar", avatarPath);
                model.addAttribute("app_path", request.getContextPath());

                model.addAttribute("action", path);
                model.addAttribute("button_value", "Изменить");
            }
            return "resumePage";
        } else {
            return "workerregpage";
        }

    }

    @GetMapping("/resumeAvatar")
    public String avatarPage(Model model) {
        return "resumeAvatar";
    }

    @PostMapping("/saveResume")
    String saveResume(@ModelAttribute("dto") ResumeDto dto, HttpServletRequest request, Model model, Authentication authentication) {


        try{
            String username = ((UserDetailImpl) authentication.getPrincipal()).getUsername();
            Worker user = userService.findWorkerByUsername(username);
            Resume resume = new Resume();
            resume.setWorker(user);
            resume.setGender(dto.getGender());
            resume.setAboutMe(dto.getAboutMe());
            resume.setCitizenship(dto.getCitizenship());
            resume.setCity(dto.getCity());
            String dateString = dto.getBirthDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dateString);
            resume.setBirthDate(date);
            resume.setEducation(dto.getEducation());
            resume.setSkills(dto.getSkills());
            resume.setWorkExperience(dto.getWorkExperience());
            resume.setProfession(dto.getProfession());
            resume.setEmail(dto.getEmail());
            resume = resumeService.save(resume);
            model.addAttribute("id", resume.getId());

            String avatarPath =
                    ( resume.getAvatar() != null) ?
                            "/imgstore/" +
                                    resume.getAvatar().getPath() +
                                    resume.getAvatar().getFileName()
                            : "/static/static/img/avatar_default.svg";
            model.addAttribute("avatar", avatarPath);
            model.addAttribute("app_path", request.getContextPath());

            return "resumeAvatar";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "resume";
        }

    }

    @PostMapping("/loadavatar")
    public String loadAvatar(@RequestParam("avatar") MultipartFile file,@RequestParam("resume_id") Long resume_id,  Authentication authentication) {

        String username = ((UserDetailImpl) authentication.getPrincipal()).getUsername();
        Worker user = userService.findWorkerByUsername(username);
        System.out.println(file.getOriginalFilename());
        log.debug(imgStorePath);
        try {
            log.debug(file.getOriginalFilename());
            log.debug(String.valueOf(file.getBytes().length));


            Resume resume = resumeService.findResumeById(resume_id);

            ImageFileData imageFileData = resume.getAvatar() != null ? resume.getAvatar() : new ImageFileData();

            imageFileData.setFileName(file.getOriginalFilename());
            imageFileData.setPath("avatar/" + String.valueOf(user.getId()) + "/");
            imageFileData.setResume(resume);

            resume.setAvatar(imageFileData);



            resumeService.save(resume);

            File path = new File(imgStorePath + "/"+imageFileData.getPath());
            if (!path.exists()) {
                path.mkdir();
            }

            try (FileOutputStream fos =
                         new FileOutputStream(new File(path, file.getOriginalFilename()))) {
                fos.write(file.getBytes());
                fos.flush();
            }
        } catch (IOException e) {
            throw new ImageException("Ошибка при записи или чтении файла");
        }

        return "redirect:/account";
    }





}
