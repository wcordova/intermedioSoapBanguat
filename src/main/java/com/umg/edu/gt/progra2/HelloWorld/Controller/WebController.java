package com.umg.edu.gt.progra2.HelloWorld.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        // Aquí puedes agregar lógica si es necesario
        return "index"; // Este es el nombre de tu archivo Thymeleaf (index.html)
    }
}
