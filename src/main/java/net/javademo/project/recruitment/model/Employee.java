package net.javademo.project.recruitment.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Employee extends User {

    private List<Skill> skills = new LinkedList<Skill>();

    public Employee(String firstName, String lastName, String middleName, String login,
                    String password, String email, List skills) {
        super(firstName, lastName, middleName, login, password, email);
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(skills, employee.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skills);
    }
}
