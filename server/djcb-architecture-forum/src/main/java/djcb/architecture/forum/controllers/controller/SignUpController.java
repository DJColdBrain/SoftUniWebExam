package djcb.architecture.forum.controllers.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {


    @GetMapping(path = "/sign-up")
    public String testGet(Model model){


        return "signup";
    }

}
