package local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartMenu{
    @RequestMapping("/index.html")
    public String exec(){
        return "start";
    }
}
