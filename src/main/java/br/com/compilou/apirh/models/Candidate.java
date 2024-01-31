package br.com.compilou.apirh.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rg;

    @NotEmpty
    private String nameCandidate;

    @NotEmpty
    private String email;

    @ManyToOne
    private Vacancy vacancy;

    public Candidate() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNameCandidate() {
        return nameCandidate;
    }

    public void setNameCandidate(String nameCandidate) {
        this.nameCandidate = nameCandidate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id) && Objects.equals(rg, candidate.rg) && Objects.equals(nameCandidate, candidate.nameCandidate) && Objects.equals(email, candidate.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rg, nameCandidate, email);
    }
}
