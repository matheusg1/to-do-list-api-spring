package one.digitalinnovation.todolistspring.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.todolistspring.model.Tarefa;
import one.digitalinnovation.todolistspring.model.Usuario;
import one.digitalinnovation.todolistspring.repository.TarefaRepository;
import one.digitalinnovation.todolistspring.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Iterable<Usuario> buscaTodosUsuarios() {
        Optional<Iterable<Usuario>> usuarioOptional = Optional.ofNullable(usuarioRepository.findAll());

        return usuarioOptional.orElseGet(() -> null);
    }

    @Override
    public Usuario criaUsuario(Usuario usuario) {
        var usuarioCriado = usuarioRepository.save(usuario);
        return usuarioCriado;
    }

    @Override
    public void deletaUsuarioPorId(long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void adicionaTarefaUsuario(Usuario usuario, Tarefa tarefa) {
        usuario.adicionaTarefa(tarefa);
        usuarioRepository.save(usuario);
    }

    @Override
    public void removeTarefaUsuario(Usuario usuario, Tarefa tarefa) {
        var usuarioBuscado = usuarioRepository.findById(usuario.getId());
        
        usuario.getTarefas().remove(tarefa);
        tarefaRepository.delete(tarefa);

        if(usuarioBuscado.isPresent()){
            var usuarioEncontrado = usuarioBuscado.get();
            usuarioRepository.save(usuarioEncontrado);
        }
    }

}
