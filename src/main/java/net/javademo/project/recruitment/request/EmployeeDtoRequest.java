package net.javademo.project.recruitment.request;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EmployeeDtoRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private List<SkillDtoRequest> skills = new LinkedList<>();

    public EmployeeDtoRequest(String firstName, String lastName, String middleName, String email, List<SkillDtoRequest> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.skills = skills;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SkillDtoRequest> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDtoRequest> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDtoRequest that = (EmployeeDtoRequest) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, email, skills);
    }
}
