package net.javademo.project.recruitment.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employer extends User {

    private String company;
    private String address;
    private Set<Vacancy> vacancies = new HashSet<>();
    private Set<Vacancy> notActiveVacancies = new  HashSet<>();

    public Employer(String firstName, String lastName, String middleName,
                    String login, String password, String  email, String company, String address) {
        super(firstName, lastName, middleName, login, password, email);
        this.company = company;
        this.address = address;
    }

    public void addEmployerVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public Set<Vacancy> getNotActiveVacancies() {
        return notActiveVacancies;
    }

    public void setNotActiveVacancies(Set<Vacancy> notActiveVacancies) {
        this.notActiveVacancies = notActiveVacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employer employer = (Employer) o;
        return Objects.equals(company, employer.company) &&
                Objects.equals(address, employer.address) &&
                Objects.equals(vacancies, employer.vacancies) &&
                Objects.equals(notActiveVacancies, employer.notActiveVacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company, address, vacancies, notActiveVacancies);
    }
}
