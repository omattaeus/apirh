package br.com.compilou.apirh.repositories;

import br.com.compilou.apirh.models.Candidate;
import br.com.compilou.apirh.models.Vacancy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    Candidate findCandidateById(Long id);

    Iterable<Candidate> findByVacancy(Vacancy vacancy);

    Candidate findByRg(String rg);

    @Query("SELECT u FROM Candidate u WHERE u.nameCandidate LIKE %?1%")
    List<Candidate> findByNameCandidateContaining(String nameCandidate);
}
