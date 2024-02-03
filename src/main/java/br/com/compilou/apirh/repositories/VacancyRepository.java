package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Vacancy;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    Vacancy findByCode(Long code);

    List<Vacancy> findByName(String name);

    @Query("SELECT v FROM Vacancy v WHERE v.name LIKE %?1%")
    List<Vacancy> findByVacancyName(String name);
}
