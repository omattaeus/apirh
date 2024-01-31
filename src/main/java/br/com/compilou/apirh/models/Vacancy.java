package br.com.compilou.apirh.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
public class Vacancy implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @NotEmpty
    private String description;
    @NotEmpty
    private String date;
    @NotEmpty
    private String wage;
    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.REMOVE)
    private List<Candidate> candidates;

    public Vacancy() {}

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(code, vacancy.code) && Objects.equals(description, vacancy.description) && Objects.equals(date, vacancy.date) && Objects.equals(wage, vacancy.wage) && Objects.equals(candidates, vacancy.candidates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description, date, wage, candidates);
    }
}
