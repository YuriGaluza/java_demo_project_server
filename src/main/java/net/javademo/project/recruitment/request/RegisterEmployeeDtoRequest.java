package net.javademo.project.recruitment.request;

import net.javademo.project.recruitment.model.Skill;

import java.util.LinkedList;
import java.util.List;

public class RegisterEmployeeDtoRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String login;
    private String password;
    private String email;
    private List<Skill> skills = new LinkedList<Skill>();

    public RegisterEmployeeDtoRequest(String firstName, String lastName, String middleName,
                                      String login, String password, String email, List skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}


