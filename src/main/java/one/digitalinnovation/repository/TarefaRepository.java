package one.digitalinnovation.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.model.Tarefa;

@Repository
public interface TarefaRepository extends CrudRepository<Tarefa, Long> {

}
