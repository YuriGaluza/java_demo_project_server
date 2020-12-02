package net.javademo.project.recruitment.dao;

import net.javademo.project.recruitment.model.*;
import net.javademo.project.recruitment.server.ServerException;

import java.util.Collection;
import java.util.Set;

public interface EmployerDao {

    void insert(Employer employer) throws ServerException;

    void userLogin(String token, Employer employer) throws ServerException;

    void userLogout(String token) throws ServerException;

    void addVacancy(Vacancy vacancy, Employer employer);

    Employer getEmployerByToken(String token);

    Collection<Vacancy> getAllVacancies();

    Set<Employee> getEmployeesBySkill(Skill skill);

    void userLogin(String token, User user) throws ServerException;

    boolean checkEmployerLogin(String enterEmployerLogin);

    Employer getEmployerByLogin(String login);

    Set<Employee> getEmployeesByVacancy(Vacancy vacancy) throws ServerException;

    boolean checkUserSession(String token) throws ServerException;

    Set<Vacancy> getVacancyForEmployer(Employer employer);
}
