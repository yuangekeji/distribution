package com.distribution.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "")
    public String index() {
        return "forward:/WEB-INF/main.jsp";
    }
}
