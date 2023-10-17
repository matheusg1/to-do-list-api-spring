package one.digitalinnovation.todolistspring.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import one.digitalinnovation.todolistspring.model.Tarefa;
import one.digitalinnovation.todolistspring.model.Usuario;
import one.digitalinnovation.todolistspring.repository.TarefaRepository;
import one.digitalinnovation.todolistspring.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        Usuario usuario1 = new Usuario();
        usuario1.setLogin("usuario1");
        usuario1.setNome("usuario1Nome");
        
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Lavar o carro");
        tarefa.setDescricao("Lavar carro na garagem");
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        tarefa.setData(formatoData.parse("2023-10-17"));

        tarefaRepository.save(tarefa);
        usuario1.adicionaTarefa(tarefa);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTitulo("Arrumar as malas");
        tarefa2.setDescricao("Arrumar as malas para a viagem"); 
        tarefa2.setData(formatoData.parse("2023-10-20")); 

        tarefaRepository.save(tarefa2);
        usuario1.adicionaTarefa(tarefa2);

        usuarioRepository.save(usuario1);
    }
}

