package net.javademo.project.recruitment.request;

import java.util.LinkedList;
import java.util.List;

public class VacancyDtoRequest {

    private String name;
    private String company;
    private int salary;
    private List<RequirementDtoRequest> requirements;
    private List<RequirementDtoRequest> upperRequirements = new LinkedList<>();
    private boolean active;
    private String getEmployeesMethod;
    private String getVacanciesMethod;

    public VacancyDtoRequest(String name, String company, int salary, List<RequirementDtoRequest> requirements,
                             boolean active) {
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

    public List<RequirementDtoRequest> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementDtoRequest> requirements) {
        this.requirements = requirements;
    }

    public List<RequirementDtoRequest> getUpperRequirements() {
        return upperRequirements;
    }

    public void setUpperRequirements(List<RequirementDtoRequest> upperRequirements) {
        this.upperRequirements = upperRequirements;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
