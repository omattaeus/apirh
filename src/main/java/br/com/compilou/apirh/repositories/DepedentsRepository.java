package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Depedents;
import br.com.compilou.apirh.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepedentsRepository extends CrudRepository<Depedents, String> {

    Iterable<Depedents> findByEmployee(Employee employee);

    Depedents findByCpf(String cpf);
    Depedents findBy(Long id);
    List<Depedents> findByName(String name);

    @Query(value = "select u from depedents u where u.name like %?1%")
    List<Depedents> findByDepedentsName(String name);
}
