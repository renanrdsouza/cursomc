package com.example.cursomc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @RequestMapping(method = RequestMethod.GET)
    public String listar() {
        return "Rest está funcionando.";
    }
}
