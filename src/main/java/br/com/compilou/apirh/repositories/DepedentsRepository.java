package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Depedents;
import br.com.compilou.apirh.models.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepedentsRepository extends CrudRepository<Depedents, Long> {

    List<Depedents> findByEmployee(Employee employee);

    Depedents findDepedentsById(Long id);

    Depedents findByCpf(String cpf);

    List<Depedents> findByName(String name);

    @Query("SELECT d FROM Depedents d WHERE d.name LIKE %?1%")
    List<Depedents> findByDependentsNameContaining(String name);
}