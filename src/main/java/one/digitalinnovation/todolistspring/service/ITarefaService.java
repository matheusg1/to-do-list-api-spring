package one.digitalinnovation.todolistspring.service;

import java.util.Date;
import java.util.List;

import one.digitalinnovation.todolistspring.model.Tarefa;

public interface ITarefaService {
    Iterable<Tarefa> buscaTodasTarefas();
    Tarefa buscaTarefaMaisProxima();
    List<Tarefa> buscaTarefasPorNomeUsuario(String nome);
    List<Tarefa> buscaTarefasPorLogin(String login);
    Tarefa buscaTarefaMaisProximaPorLogin(String login);
    Iterable<Tarefa> buscaTarefasPorLoginEPeriodo(String login, Date data1, Date data2);
    Tarefa adicionaTarefa(Tarefa tarefa);
}
