package one.digitalinnovation.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.model.Tarefa;
import one.digitalinnovation.model.Usuario;
import one.digitalinnovation.repository.TarefaRepository;
import one.digitalinnovation.repository.UsuarioRepository;

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
    public void adicionaTarefaUsuario(String loginUsuario, Tarefa tarefa) {
        List<Usuario> usuarios = IterableUtils.toList(usuarioRepository.findAll());
        Usuario usuarioBuscado = usuarios.stream().filter(x -> x.getLogin().equals(loginUsuario)).findFirst()
                .orElse(null);

        if (usuarioBuscado != null) {
            usuarioBuscado.adicionaTarefa(tarefa);
        }
    }

    @Override
    public void removeTarefaUsuario(String loginUsuario, String titulo) {
        List<Usuario> usuarios = IterableUtils.toList(usuarioRepository.findAll());
        Usuario usuarioBuscado = usuarios.stream().filter(x -> x.getLogin().equals(loginUsuario)).findFirst()
                .orElse(null);

        if (usuarioBuscado != null) {
            var tarefaBuscada = usuarioBuscado.getTarefas().stream().filter(x -> x.getTitulo().equals(titulo))
                    .findFirst();

            if (tarefaBuscada != null) {
                usuarioBuscado.removeTarefa(titulo);
            }
        }

    }

}
