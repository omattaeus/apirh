package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Employee findById(Long id);

    Employee findByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %?1%")
    List<Employee> findByNames(String name);
}
