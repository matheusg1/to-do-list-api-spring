package one.digitalinnovation.service;

import one.digitalinnovation.model.Tarefa;
import one.digitalinnovation.model.Usuario;

public interface IUsuarioService {
    Usuario criaUsuario(Usuario usuario);
    void deletaUsuarioPorId(long id);
    Iterable<Usuario> buscaTodosUsuarios();
    void adicionaTarefaUsuario(String loginUsuario, Tarefa tarefa);
    void removeTarefaUsuario(String loginUsuario, String titulo);
}
