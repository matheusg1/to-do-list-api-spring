package one.digitalinnovation.todolistspring.model;

public class RemoveTarefaDTO {
    private String tituloTarefa;
    private String login;
    
    public RemoveTarefaDTO() {
    }
    public String getTituloTarefa() {
        return tituloTarefa;
    }
    public void setTituloTarefa(String tituloTarefa) {
        this.tituloTarefa = tituloTarefa;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    
}
