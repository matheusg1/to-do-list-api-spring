package one.digitalinnovation.todolistspring.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Long getId() {
        return id;
    }

    private String nome;
    private String login;
    
    @OneToMany
    private List<Tarefa> tarefas = new ArrayList<>();

    public Usuario(String nome, String login) {
        this.nome = nome;
        this.login = login;
    }
    public Usuario() {
    }
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
        tarefa.setUsuario(this);        
    }

    public void removeTarefa(String titulo){
        var tarefaParaRemover = this.tarefas.stream()
        .filter(t -> t.getTitulo().equals(titulo)).findFirst();

        if(tarefaParaRemover.isPresent()){
            tarefas.remove(tarefaParaRemover.get());
        }        
    }
}
