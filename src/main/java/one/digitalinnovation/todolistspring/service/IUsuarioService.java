package one.digitalinnovation.todolistspring.service;

import one.digitalinnovation.todolistspring.model.Tarefa;
import one.digitalinnovation.todolistspring.model.Usuario;

public interface IUsuarioService {
    Usuario criaUsuario(Usuario usuario);
    void deletaUsuarioPorId(long id);
    Iterable<Usuario> buscaTodosUsuarios();
    void adicionaTarefaUsuario(Usuario usuario, Tarefa tarefa);
    void removeTarefaUsuario(Usuario usuario, Tarefa tarefa);
}
