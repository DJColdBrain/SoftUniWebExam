package djcb.architecture.forum.controllers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


    @GetMapping(path = "/")
    public String testGet(Model model){
        return "template";
    }

}
