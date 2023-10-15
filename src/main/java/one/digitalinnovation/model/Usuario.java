package one.digitalinnovation.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nome;
    private String login;

    @ManyToOne
    private List<Tarefa> tarefas;

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void adicionaTarefa(Tarefa tarefa){
        this.tarefas.add(tarefa);
    }

    public void removeTarefa(String titulo){
        var tarefaParaRemover = this.tarefas.stream()
        .filter(t -> t.getTitulo().equals(titulo)).findFirst();

        if(tarefaParaRemover.isPresent()){
            tarefas.remove(tarefaParaRemover.get());
        }
    }
}
