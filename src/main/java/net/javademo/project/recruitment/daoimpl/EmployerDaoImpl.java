package net.javademo.project.recruitment.daoimpl;

import net.javademo.project.recruitment.dao.EmployerDao;
import net.javademo.project.recruitment.database.DataBase;
import net.javademo.project.recruitment.model.*;
import net.javademo.project.recruitment.server.ServerException;

import java.util.Collection;
import java.util.Set;

public class EmployerDaoImpl implements EmployerDao {

    public void insert(Employer employer) throws ServerException {
        DataBase.getInstance().insert(employer);
    }

    public void userLogin(String token, Employer employer) throws ServerException {
        DataBase.getInstance().userLogin(token, employer);
    }

    public void userLogout(String  token) throws ServerException {
        DataBase.getInstance().userLogout(token);
    }

    public void addVacancy(Vacancy vacancy, Employer employer) {
        DataBase.getInstance().addVacancy(vacancy, employer);
    }

    public Employer getEmployerByToken(String token) {
        return DataBase.getInstance().getEmployerByToken(token);
    }

    public Collection<Vacancy> getAllVacancies() {
        return DataBase.getInstance().getAllVacancies();
    }

    public Set<Employee> getEmployeesBySkill(Skill skill) {
        return DataBase.getInstance().getEmployeesBySkill(skill);
    }

    public void userLogin(String token, User user) throws ServerException {
        DataBase.getInstance().userLogin(token, user);
    }

    public boolean checkEmployerLogin(String enterEmployerLogin) {
        return DataBase.getInstance().checkEmployerLogin(enterEmployerLogin);
    }

    public Employer getEmployerByLogin(String login) {
        return DataBase.getInstance().getEmployerByLogin(login);
    }

    public Set<Employee> getEmployeesByVacancy(Vacancy vacancy) throws ServerException {
        return DataBase.getInstance().getEmployeesByVacancy(vacancy);
    }

    public boolean checkUserSession(String token) throws ServerException {
        return DataBase.getInstance().checkUserSession(token);
    }

    public Set<Vacancy> getVacancyForEmployer(Employer employer) {
        return DataBase.getInstance().getVacancyForEmployer(employer);
    }
}
