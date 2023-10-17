package one.digitalinnovation.todolistspring.model;

public class AdicionaTarefaDTO {
    private Tarefa tarefa;
    private String login;
    
    public AdicionaTarefaDTO() {
    }
    public Tarefa getTarefa() {
        return tarefa;
    }
    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}
