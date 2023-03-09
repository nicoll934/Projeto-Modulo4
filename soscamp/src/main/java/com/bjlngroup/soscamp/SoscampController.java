package com.bjlngroup.soscamp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoscampController {
    @GetMapping("/habilidades")
    public Habilidades listaHabilidades (){
        Habilidades habilidades = new Habilidades(1,"comunicação");
        return habilidades;
    }


}
