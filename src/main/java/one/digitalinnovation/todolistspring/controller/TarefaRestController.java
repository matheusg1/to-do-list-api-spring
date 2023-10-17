package one.digitalinnovation.todolistspring.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.todolistspring.model.AdicionaTarefaDTO;
import one.digitalinnovation.todolistspring.model.RemoveTarefaDTO;
import one.digitalinnovation.todolistspring.model.Tarefa;
import one.digitalinnovation.todolistspring.model.Usuario;
import one.digitalinnovation.todolistspring.service.TarefaService;
import one.digitalinnovation.todolistspring.service.UsuarioService;

@RestController
public class TarefaRestController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TarefaService tarefaService;

    @GetMapping("/buscaTodosUsuarios")
    public List<Usuario> buscaTodosUsuarios(){
        return IterableUtils.toList(usuarioService.buscaTodosUsuarios());
    }

    @PostMapping("/criaUsuario")
    public Usuario criaUsuario(@RequestBody Usuario usuario){
        return usuarioService.criaUsuario(usuario);
    }

    @PostMapping("/adicionaTarefaUsuario")
    public void adicionaTarefaUsuario(@RequestBody AdicionaTarefaDTO dto){
        
        var usuarioBuscado = IterableUtils.toList(usuarioService.buscaTodosUsuarios()).stream().filter(x -> x.getLogin().equals(dto.getLogin())).findFirst();        
        if(usuarioBuscado.isPresent()){
            var usuarioEncontrado = usuarioBuscado.get();

            var tarefa = tarefaService.adicionaTarefa(dto.getTarefa());

            usuarioService.adicionaTarefaUsuario(usuarioEncontrado, tarefa);
        }        
    }

    @DeleteMapping("/removeTarefaUsuario")
    public void removeTarefaUsuario(@RequestBody RemoveTarefaDTO dto){
        
        var usuarioBuscado = IterableUtils.toList(usuarioService.buscaTodosUsuarios()).stream().filter(x -> x.getLogin().equals(dto.getLogin())).findFirst();        
        
        if(usuarioBuscado.isPresent()){
            var usuarioEncontrado = usuarioBuscado.get();
            var tarefas = tarefaService.buscaTarefasPorLogin(dto.getLogin());
            
            //tarefas.removeIf(x -> x.getTitulo().equalsIgnoreCase(dto.getTituloTarefa()));
            
            var buscaTarefa = tarefas.stream().filter(x -> x.getTitulo().equalsIgnoreCase(dto.getTituloTarefa())).findFirst();
            
            if(buscaTarefa.isPresent()){
                var tarefaEncontrada = buscaTarefa.get();
                
                usuarioService.removeTarefaUsuario(usuarioEncontrado, tarefaEncontrada);
            }
        }        
    }

    @GetMapping("/buscaTarefasPorLogin/{login}")
        public List<Tarefa> buscaTarefasPorLogin(@PathVariable("login") String login){
        return IterableUtils.toList(tarefaService.buscaTarefasPorLogin(login));
    }

    @GetMapping("/buscaTodasTarefas")
        public List<Tarefa> buscaTodasTarefas(){
        return IterableUtils.toList(tarefaService.buscaTodasTarefas());
    }

}
