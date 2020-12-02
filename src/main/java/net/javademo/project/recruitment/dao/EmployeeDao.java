package net.javademo.project.recruitment.dao;

import net.javademo.project.recruitment.model.Employee;
import net.javademo.project.recruitment.model.Skill;
import net.javademo.project.recruitment.model.User;
import net.javademo.project.recruitment.model.Vacancy;
import net.javademo.project.recruitment.server.ServerException;

import java.util.Collection;
import java.util.Set;

public interface EmployeeDao {

    void insert(Employee employee) throws ServerException;

    void userLogin(String token, User user) throws ServerException;

    void userLogout(String token) throws ServerException;

    Employee getEmployeeByToken(String token);

    Collection<Vacancy> getAllVacancies();

    void insertSkill(Skill skill, Employee employee) throws ServerException;

    Employee getEmployeeByLogin(String login);

    boolean checkUserToken(String token);

    boolean checkEmployeeLogin(String enterEmployeeLogin);

    boolean checkUserSession(String token) throws ServerException;

    Set<Vacancy> getVacanciesByVacancy(Vacancy vacancy) throws ServerException;

    void addSkill(Skill skill, Employee employee);
}

