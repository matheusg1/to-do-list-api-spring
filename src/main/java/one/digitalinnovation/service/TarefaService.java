package one.digitalinnovation.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.IterableUtils;

import one.digitalinnovation.model.Tarefa;
import one.digitalinnovation.model.Usuario;
import one.digitalinnovation.repository.TarefaRepository;
import one.digitalinnovation.repository.UsuarioRepository;

@Service
public class TarefaService implements ITarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Iterable<Tarefa> buscaTodasTarefas() {
        Optional<Iterable<Tarefa>> tarefasOptional = Optional.ofNullable(tarefaRepository.findAll());

        return tarefasOptional.orElseGet(() -> null);
    }

    @Override
    public Tarefa buscaTarefaMaisProxima() {
        var tarefas = tarefaRepository.findAll();

        List<Tarefa> tarefasList = StreamSupport.stream(tarefas.spliterator(), false)
                .collect(Collectors.toList());

        tarefasList.sort(Comparator.comparing(Tarefa::getData));

        if (!tarefasList.isEmpty()) {
            return tarefasList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Tarefa> buscaTarefasPorNomeUsuario(String nome) {
        List<Usuario> listaUsuario = IterableUtils.toList(usuarioRepository.findAll());

        var usuarioBuscado = listaUsuario.stream().filter(x -> x.getNome().equals(nome)).findFirst();

        if (usuarioBuscado.isPresent()) {
            return IterableUtils.toList(usuarioBuscado.get().getTarefas());
        }
        ;
        return null;
    }

    @Override
    public List<Tarefa> buscaTarefasPorLogin(String login) {
        List<Usuario> listaUsuario = IterableUtils.toList(usuarioRepository.findAll());
        var usuarioBuscado = listaUsuario.stream().filter(x -> x.getLogin().equals(login)).findFirst();

        if (usuarioBuscado.isPresent()) {
            return IterableUtils.toList(usuarioBuscado.get().getTarefas());
        }
        ;
        return null;
    }

    @Override
    public Tarefa buscaTarefaMaisProximaPorLogin(String login) {
        List<Usuario> listaUsuario = IterableUtils.toList(usuarioRepository.findAll());
        var usuarioBuscado = listaUsuario.stream().filter(x -> x.getLogin().equals(login)).findFirst();

        if (usuarioBuscado.isPresent()) {
            var tarefasUsuario = usuarioBuscado.get().getTarefas();
            tarefasUsuario.sort(Comparator.comparing(Tarefa::getData).reversed());
            return tarefasUsuario.get(0);
        }
        return null;
    }

    @Override
    public Iterable<Tarefa> buscaTarefasPorLoginEPeriodo(String login, Date data1, Date data2) {
        List<Usuario> listaUsuario = IterableUtils.toList(usuarioRepository.findAll());
        var usuarioBuscado = listaUsuario.stream()
                .filter(x -> x.getLogin().equals(login)).findFirst();

        if (usuarioBuscado.isPresent()) {
            var usuarioEncontrado = usuarioBuscado.get();
            return usuarioEncontrado.getTarefas().stream().filter(x -> x.getData().before(data2)
                    && x.getData().after(data1)).toList();                    
        }
        return null;
    }
}
