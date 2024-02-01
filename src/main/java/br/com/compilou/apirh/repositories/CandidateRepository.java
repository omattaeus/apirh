package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Candidate;
import br.com.compilou.apirh.models.Vacancy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, String> {

    Iterable<Candidate> findByVacancy(Vacancy vacancy);

    Candidate findByRg(String rg);

    Candidate findById(Long id);

    @Query("SELECT u FROM Candidate u WHERE u.nameCandidate like %?1%")
    List<Candidate> findByNameCandidate(String nameCandidate);


}
