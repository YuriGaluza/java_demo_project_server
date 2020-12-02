package net.javademo.project.recruitment.response;

import net.javademo.project.recruitment.request.EmployeeDtoRequest;

import java.util.HashSet;
import java.util.Set;

public class GetEmployeesByVacancyDtoResponse {
    Set<EmployeeDtoRequest> employeeSet = new HashSet<>();

    public GetEmployeesByVacancyDtoResponse(Set<EmployeeDtoRequest> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public Set<EmployeeDtoRequest> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<EmployeeDtoRequest> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
