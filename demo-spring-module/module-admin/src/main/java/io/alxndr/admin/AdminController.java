package io.alxndr.admin;

import io.alxndr.core.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    private CoreService coreService;


    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return coreService.isOk();
    }

}
