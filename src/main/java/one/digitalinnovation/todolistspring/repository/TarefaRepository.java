package one.digitalinnovation.todolistspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.todolistspring.model.Tarefa;

@Repository
public interface TarefaRepository extends CrudRepository<Tarefa, Long> {

}
