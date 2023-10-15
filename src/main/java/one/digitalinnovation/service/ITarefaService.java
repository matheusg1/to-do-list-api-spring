package one.digitalinnovation.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import one.digitalinnovation.model.Tarefa;

public interface ITarefaService {
    Iterable<Tarefa> buscaTodasTarefas();
    Tarefa buscaTarefaMaisProxima();
    List<Tarefa> buscaTarefasPorNomeUsuario(String nome);
    List<Tarefa> buscaTarefasPorLogin(String login);
    Tarefa buscaTarefaMaisProximaPorLogin(String login);
    Iterable<Tarefa> buscaTarefasPorLoginEPeriodo(String login, Date data1, Date data2);
}
