package hac.ex4.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this controller handles all exceptions/errors and displays a friendly
 * error page
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * error page
     * @return error page
     */
    @RequestMapping("/error")
    public String handleError() {
        return "error/error";
    }

}