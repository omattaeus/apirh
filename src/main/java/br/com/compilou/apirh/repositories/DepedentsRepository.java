package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Depedents;
import br.com.compilou.apirh.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepedentsRepository extends CrudRepository<Depedents, String> {

    Iterable<Depedents> findByEmployee(Employee employee);

    Depedents findByCpf(String cpf);

    Depedents findById(Long id);

    List<Depedents> findByName(String name);

    @Query("SELECT d FROM Depedents d WHERE d.name LIKE %?1%")
    List<Depedents> findByDependentsName(String name);
}
