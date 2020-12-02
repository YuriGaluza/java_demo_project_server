package net.javademo.project.recruitment.daoimpl;

import net.javademo.project.recruitment.dao.EmployeeDao;
import net.javademo.project.recruitment.database.DataBase;
import net.javademo.project.recruitment.model.Employee;
import net.javademo.project.recruitment.model.Skill;
import net.javademo.project.recruitment.model.User;
import net.javademo.project.recruitment.model.Vacancy;
import net.javademo.project.recruitment.server.ServerException;

import java.util.Collection;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {

    public void insert(Employee employee) throws ServerException {
        DataBase.getInstance().insert(employee);
    }

    public void userLogin(String token, User user) throws ServerException {
        DataBase.getInstance().userLogin(token, user);
    }

    public void userLogout(String token) throws ServerException {
        DataBase.getInstance().userLogout(token);
    }

    public Employee getEmployeeByToken(String token) {
        return DataBase.getInstance().getEmployeeByToken(token);
    }

    public Collection<Vacancy> getAllVacancies() {
        return DataBase.getInstance().getAllVacancies();
    }

    public void insertSkill(Skill skill, Employee employee) throws ServerException {
        DataBase.getInstance().insertSkill(skill, employee);
    }

    public Employee getEmployeeByLogin(String login) {
        return DataBase.getInstance().getEmployeeByLogin(login);
    }

    public boolean checkUserToken(String token) {
        return DataBase.getInstance().checkUserByToken(token);
    }

    public boolean checkEmployeeLogin(String enterEmployeeLogin) {
        return DataBase.getInstance().checkEmployeeLogin(enterEmployeeLogin);
    }

    public boolean checkUserSession(String token) throws ServerException {
        return DataBase.getInstance().checkUserSession(token);
    }

    public Set<Vacancy> getVacanciesByVacancy(Vacancy vacancy) throws ServerException {
        return DataBase.getInstance().getVacanciesByVacancy(vacancy);
    }

    public void addSkill(Skill skill, Employee employee) {
        DataBase.getInstance().addSkill(skill, employee);
    }
}
