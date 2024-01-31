package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Employee findById(Long id);

    Employee findByName(String name);

    @Query(value = "select u from employee u where u.name like %?1%")
    List<Employee> findByNames(String nome);
}
