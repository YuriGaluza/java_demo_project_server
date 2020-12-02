package net.javademo.project.recruitment.request;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AddVacancyDtoRequest {

    private String name;
    private String company;
    private int salary;
    private List<RequirementDtoRequest> requirements = new LinkedList<>();
    private List<RequirementDtoRequest> upperRequirements = new LinkedList<>();
    private boolean active;
    private String getEmployeesMethod;
    private String getVacanciesMethod;
    private String token;

    public AddVacancyDtoRequest(String name, String company, int salary, List<RequirementDtoRequest> requirements, boolean active, String token) {
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.requirements = requirements;
        this.active = active;
        this.token = token;
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

    public List<RequirementDtoRequest> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementDtoRequest> requirements) {
        this.requirements = requirements;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<RequirementDtoRequest> getUpperRequirements() {
        return upperRequirements;
    }

    public void setUpperRequirements(List<RequirementDtoRequest> upperRequirements) {
        this.upperRequirements = upperRequirements;
    }

    public String getGetEmployeesMethod() {
        return getEmployeesMethod;
    }

    public void setGetEmployeesMethod(String getEmployeesMethod) {
        this.getEmployeesMethod = getEmployeesMethod;
    }

    public String getGetVacanciesMethod() {
        return getVacanciesMethod;
    }

    public void setGetVacanciesMethod(String getVacanciesMethod) {
        this.getVacanciesMethod = getVacanciesMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddVacancyDtoRequest that = (AddVacancyDtoRequest) o;
        return salary == that.salary &&
                active == that.active &&
                Objects.equals(name, that.name) &&
                Objects.equals(company, that.company) &&
                Objects.equals(requirements, that.requirements) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, salary, requirements, active, token);
    }
}
