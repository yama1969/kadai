package local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaffMenu {
    @RequestMapping("/orderSearch.html")
    public String exec(){
        return "staff";
    }
}
