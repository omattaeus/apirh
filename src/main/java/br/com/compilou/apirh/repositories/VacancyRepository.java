package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Vacancy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends CrudRepository<Vacancy, String> {

    Vacancy findByCode(Long code);

    List<Vacancy> findByName(String name);

    @Query("SELECT v FROM Vacancy v WHERE v.name LIKE %?1%")
    List<Vacancy> findByVacancyName(String name);
}