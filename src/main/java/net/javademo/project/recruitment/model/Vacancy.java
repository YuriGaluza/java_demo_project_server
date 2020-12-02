package net.javademo.project.recruitment.model;

import net.javademo.project.recruitment.server.ServerException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Vacancy {

    private String name;
    private String company;
    private int salary;
    private List<Requirement> requirements = new LinkedList<>();
    private List<Requirement> upperRequirements = new LinkedList<>();
    private boolean active;
    private GetEmployeesMethod getEmployeesMethod;
    private GetVacanciesMethod getVacanciesMethod;

    public Vacancy(String name, String company, int salary, List<Requirement> requirements, boolean active) throws ServerException {
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.requirements = requirements;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public void addRequirement(Requirement requirement) {
        requirements.add(requirement);
    }


    public void removeRequirements(Requirement requirement) {
        requirements.remove(requirement);
    }

    public void clearRequirements() {
        requirements.clear();
    }

    public void addLowerRequirement(Requirement requirement) {
        upperRequirements.add(requirement);
    }

    public void removeLowerRequirements(Requirement requirement) {
        upperRequirements.remove(requirement);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Requirement> getUpperRequirements() {
        return upperRequirements;
    }

    public void setUpperRequirements(List<Requirement> upperRequirements) {
        this.upperRequirements = upperRequirements;
    }

    public GetEmployeesMethod getGetEmployeesMethod() {
        return getEmployeesMethod;
    }

    public void setGetEmployeesMethod(GetEmployeesMethod getEmployeesMethod) {
        this.getEmployeesMethod = getEmployeesMethod;
    }

    public GetVacanciesMethod getGetVacanciesMethod() {
        return getVacanciesMethod;
    }

    public void setGetVacanciesMethod(GetVacanciesMethod getVacanciesMethod) {
        this.getVacanciesMethod = getVacanciesMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return salary == vacancy.salary &&
                active == vacancy.active &&
                Objects.equals(name, vacancy.name) &&
                Objects.equals(company, vacancy.company) &&
                Objects.equals(requirements, vacancy.requirements) &&
                Objects.equals(upperRequirements, vacancy.upperRequirements) &&
                getEmployeesMethod == vacancy.getEmployeesMethod &&
                getVacanciesMethod == vacancy.getVacanciesMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, salary, requirements, upperRequirements, active, getEmployeesMethod, getVacanciesMethod);
    }
}