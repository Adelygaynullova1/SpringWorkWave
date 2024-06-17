package semestrWork.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class GetPhotoController {

    Logger log = LoggerFactory.getLogger(GetPhotoController.class);
    private String defaultImg = "<?xml version=\"1.0\" encoding=\"utf-8\"?><svg width=\"800px\" height=\"800px\" viewBox=\"0 0 16 16\" xmlns=\"http://www.w3.org/2000/svg\"><g color=\"#000000\" font-weight=\"400\" font-family=\"Ubuntu\" letter-spacing=\"0\" word-spacing=\"0\" white-space=\"normal\" fill=\"gray\"><path d=\"M8 2a2.84 2.84 0 0 0-1.12.221c-.345.141-.651.348-.906.615v.003l-.001.002c-.248.269-.44.592-.574.96-.137.367-.203.769-.203 1.2 0 .435.065.841.203 1.209.135.361.327.68.574.95l.001.002c.254.267.558.477.901.624v.003c.346.141.723.21 1.12.21.395 0 .77-.069 1.117-.21v-.002c.343-.147.644-.357.892-.625.255-.268.45-.59.586-.952.138-.368.204-.774.204-1.21h.01c0-.43-.065-.831-.203-1.198a2.771 2.771 0 0 0-.585-.963 2.5 2.5 0 0 0-.897-.618A2.835 2.835 0 0 0 7.999 2zM8.024 10.002c-2.317 0-3.561.213-4.486.91-.462.35-.767.825-.939 1.378-.172.553-.226.975-.228 1.71L8 14.002h5.629c-.001-.736-.052-1.159-.225-1.712-.172-.553-.477-1.027-.94-1.376-.923-.697-2.124-.912-4.44-.912z\" style=\"line-height:125%;-inkscape-font-specification:'Ubuntu, Normal';font-variant-ligatures:normal;font-variant-position:normal;font-variant-caps:normal;font-variant-numeric:normal;font-variant-alternates:normal;font-feature-settings:normal;text-indent:0;text-align:start;text-decoration-line:none;text-decoration-style:solid;text-decoration-color:#000000;text-transform:none;text-orientation:mixed;shape-padding:0;isolation:auto;mix-blend-mode:normal\"overflow=\"visible\"/></g></svg>";
    private String imgStorePath = "C:/Users/User1/Desktop/WorkW" ;


    @GetMapping("/imgstore/**")
    @ResponseBody
    public void getImageFromImageStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug(request.getServletPath());
        log.debug(request.getServletPath().substring("/imgstore/".length()));
        byte[] content = null;
        File imageFullPath = new File(imgStorePath, request.getServletPath().substring("/imgstore/".length())); // берет после "/imgstore/") и обрезает начальную часть "/imgstore/", чтобы получить оставшуюся часть пути к изображению
        log.debug(imageFullPath.getAbsolutePath());
        try (FileInputStream fis = new FileInputStream(imageFullPath)) {
            content = fis.readAllBytes();
            log.debug(String.valueOf(content.length));
        } catch (Exception e) {
            content = defaultImg.getBytes();
        }

        response.setContentLength(content.length);
        response.getOutputStream().write(content);
    }
}
