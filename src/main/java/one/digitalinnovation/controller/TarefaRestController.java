package one.digitalinnovation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.repository.TarefaRepository;

@RestController
public class TarefaRestController {
    @Autowired
    TarefaRepository tarefaRepository;

    @GetMapping("/teste")
    public String getExemplo(){
        return "Exemplo";
    }
}
