package br.com.compilou.apirh.models;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String date;
    private String email;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<Depedents> dependents;

    public Employee() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Depedents> getDependents() {
        return dependents;
    }

    public void setDependents(List<Depedents> dependents) {
        this.dependents = dependents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(date, employee.date) && Objects.equals(email, employee.email) && Objects.equals(dependents, employee.dependents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, email, dependents);
    }
}
