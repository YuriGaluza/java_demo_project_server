package net.javademo.project.recruitment.response;

public class VacancyDtoResponse {

    private String name;
    private String company;
    private int salary;

    public VacancyDtoResponse(String name, String company, int salary) {
        this.name = name;
        this.company = company;
        this.salary = salary;
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
}
