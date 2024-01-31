package br.com.compilou.apirh.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "depedents")
public class Depedents {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String name;
    private String birthday;

    @ManyToOne
    private Employee employee;

    public Depedents() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depedents depedents = (Depedents) o;
        return Objects.equals(id, depedents.id) && Objects.equals(cpf, depedents.cpf) && Objects.equals(name, depedents.name) && Objects.equals(birthday, depedents.birthday) && Objects.equals(employee, depedents.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, name, birthday, employee);
    }
}
