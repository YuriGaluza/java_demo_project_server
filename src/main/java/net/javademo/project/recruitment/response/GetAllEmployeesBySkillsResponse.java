package net.javademo.project.recruitment.response;

import net.javademo.project.recruitment.request.EmployeeDtoRequest;

import java.util.Set;

public class GetAllEmployeesBySkillsResponse {

    Set<EmployeeDtoRequest> employees;

    public GetAllEmployeesBySkillsResponse(Set<EmployeeDtoRequest> employees) {
        this.employees = employees;
    }
}
