package net.javademo.project.recruitment.database;

import com.google.common.collect.*;
import net.javademo.project.recruitment.model.*;
import net.javademo.project.recruitment.server.ServerErrorCode;
import net.javademo.project.recruitment.server.ServerException;

import java.io.Serializable;
import java.util.*;

public class DataBase implements Serializable {

    private static DataBase instance;

    public static synchronized DataBase
    getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    private static final long serialVersionUid = 4L;
    private Map<String, User> mapUsers = new HashMap<>();
    private Map<String, User> userSession = new HashMap<>();
    private Map<Employer, List<Vacancy>> mapVacancies = new HashMap<>();

    private TreeMultimap<Skill, Employee> mapOfSkillAndEmployee = TreeMultimap.create((o1, o2) -> {
        int skillComparableResult = o1.getName().compareTo(o2.getName());
        return skillComparableResult != 0 ? skillComparableResult : o1.getLevel() - o2.getLevel();
    }, (o1, o2) -> {
        int employeeNameResult = o1.getLogin().compareTo(o2.getLogin());
        return employeeNameResult != 0 ? employeeNameResult : o1.getFirstName().compareTo(o2.getFirstName());
    });

    private TreeMultimap<Requirement, Vacancy> mapOfVacancyAndRequirements = TreeMultimap.create((o1, o2) -> {
        int requirementComparableResult = o1.getName().compareTo(o2.getName());
        return requirementComparableResult != 0 ? requirementComparableResult : o1.getLevel() - o2.getLevel();
    }, (o1, o2) -> {
        int vacancyComparableResult = o1.getCompany().compareTo(o2.getCompany());
        return vacancyComparableResult != 0 ? vacancyComparableResult : o1.getName().compareTo(o2.getName());
    });

    public void addVacancy(Vacancy vacancy, Employer employer) {
        for (Requirement requirement : vacancy.getRequirements()) {
            mapOfVacancyAndRequirements.put(requirement, vacancy);
        } employer.addEmployerVacancy(vacancy);
    }

    public void addSkill(Skill skill, Employee employee) {
        getEmployeeByLogin(employee.getLogin()).getSkills().add(skill);
        mapOfSkillAndEmployee.put(skill, employee);
    }

    public Set<Vacancy> getVacancyForEmployer(Employer employer) {
        return employer.getVacancies();
    }

    public boolean checkUserByToken(String token) {
        return userSession.containsKey(token);
    }

    public Collection<Vacancy> getAllVacancies() {
        return mapOfVacancyAndRequirements.values();
    }

    public void insert(Employer employer) throws ServerException {
        if (mapUsers.putIfAbsent(employer.getLogin(), employer) != null)
        throw new ServerException(ServerErrorCode.DUPLICATE_LOGIN);
    }

    public void insert(Employee employee) throws ServerException {
        if (mapUsers.putIfAbsent(employee.getLogin(), employee) != null)
        throw new ServerException(ServerErrorCode.DUPLICATE_LOGIN);
    }

    public boolean checkEmployerLogin(String enterEmployerLogin) {
        return mapUsers.containsKey(enterEmployerLogin);
    }

    public boolean checkEmployeeLogin(String enterEmployeeLogin) {
        return mapUsers.containsKey(enterEmployeeLogin);
    }

    public void userLogin(String token, User user) throws ServerException {
        if (userSession.putIfAbsent(token, user) != null)
            throw new ServerException(ServerErrorCode.ALREADY_LOGIN);
    }

    public void userLogout(String token) throws ServerException{
        if (userSession.remove(token) == null)
            throw new ServerException(ServerErrorCode.ALREADY_LOGOUT);
    }

    public boolean checkUserSession(String token) throws ServerException {
        if (!userSession.containsKey(token)) {
            throw new ServerException(ServerErrorCode.INVALID_TOKEN);
        } return true;
    }

    public Employer getEmployerByToken(String token) {
        return (Employer) userSession.get(token);
    }

    public Employee getEmployeeByToken(String token) {
        return (Employee) userSession.get(token);
    }

    public Employee getEmployeeByLogin(String login) {
        return (Employee) mapUsers.get(login);
    }

    public Employer getEmployerByLogin(String login) {
        return (Employer) mapUsers.get(login);
    }

    public void insertSkill(Skill skill, Employee employee) throws ServerException {
        employee.addSkill(skill);
        mapOfSkillAndEmployee.put(skill, employee);
    }

    public Set<Employee> getEmployeesBySkill(Skill skill) {
        return mapOfSkillAndEmployee.get(skill);
    }

    public Set<Employee> getEmployeesAllSkills(List<Skill> skills) {
        Set<Employee> employees = mapOfSkillAndEmployee.get(skills.get(0));
        for (int i = 1; i < skills.size(); i++) {
            employees.retainAll(mapOfSkillAndEmployee.get(skills.get(i)));
        } return employees;
    }

    public Set<Employee> getEmployeesByAtLeastSkill(List<Skill> skills) {
        Set<Employee> employees = new HashSet<>();
        for (Skill skill : skills) {
            employees.addAll(mapOfSkillAndEmployee.get(skill));
        } return employees;
    }

    public Set<Employee> getAllEmployeesBySkillsAndLevel(List<Skill> lowerSkills, List<Skill> upperSkills) {
        Set<Employee> employees = new HashSet<>();
        for (int i = 0; i < lowerSkills.size(); i++) {
            for (Skill skill : mapOfSkillAndEmployee.keySet().subSet(lowerSkills.get(i),true, upperSkills.get(i),true)) {
                employees.addAll(mapOfSkillAndEmployee.get(skill));
            }
        } return employees;
    }

    public Set<Employee> getEmployeesByVacancy(Vacancy vacancy) throws ServerException {
        List<Skill> skillList = new LinkedList<>();
        Set<Employee> result = null;
        for (Requirement requirement : vacancy.getRequirements()) {
            skillList.add(new Skill(requirement.getName(), requirement.getLevel()));
        }
        GetEmployeesMethod getEmployeesMethod = vacancy.getGetEmployeesMethod();
        switch (getEmployeesMethod) {
            case GET_EMPLOYEES_ALL_SKILLS:
                result = getEmployeesAllSkills(skillList);
                break;
            case GET_EMPLOYEES_BY_AT_LEAST_SKILL:
                result = getEmployeesByAtLeastSkill(skillList);
                break;
            case GET_ALL_EMPLOYEES_BY_SKILL_AND_LEVEL: {
                List<Skill> upperSkills = new LinkedList<>();
                List<Skill> lowerSkills = new LinkedList<>();
                for (Requirement requirement : vacancy.getUpperRequirements()) {
                    upperSkills.add(new Skill(requirement.getName(), requirement.getLevel()));
                }
                for (Requirement requirement : vacancy.getRequirements()) {
                    lowerSkills.add(new Skill(requirement.getName(), requirement.getLevel()));
                } result = getAllEmployeesBySkillsAndLevel(lowerSkills, upperSkills);
                break;
            }
        } return result;
    }

    public Set<Vacancy> getVacanciesBySkill(Requirement requirement) {
        return mapOfVacancyAndRequirements.get(requirement);
    }

    public Set<Vacancy> getAllVacanciesByRequirements(List<Requirement> requirements) {
        Set<Vacancy> vacancies = mapOfVacancyAndRequirements.get(requirements.get(0));
        for (int i = 1; i < requirements.size() ; i++) {
            vacancies.retainAll(mapOfVacancyAndRequirements.get(requirements.get(i)));
            } return vacancies;
    }

    public Set<Vacancy> getVacanciesByAtLeastRequirement(List<Requirement> requirements) {
        Set<Vacancy> vacancies = new HashSet<>();
        for (Requirement requirement : requirements) {
            vacancies.addAll(mapOfVacancyAndRequirements.get(requirement));
        } return vacancies;
    }

    public Set<Vacancy> getAllVacanciesByRequirementsAndLevel(List<Requirement> lowerRequirements, List<Requirement> upperRequirements) {
        Set<Vacancy> vacancies = new HashSet<>();
        for (int i = 0; i < lowerRequirements.size(); i++) {
            for (Requirement requirement : mapOfVacancyAndRequirements.keySet().subSet(lowerRequirements.get(i), true, upperRequirements.get(i), true)) {
                vacancies.addAll(mapOfVacancyAndRequirements.get(requirement));
            }
        } return vacancies;
    }

    public Set<Vacancy> getVacanciesByVacancy(Vacancy vacancy) {
        List<Requirement> requirementListList = vacancy.getRequirements();
        Set<Vacancy> result = null;
        GetVacanciesMethod getVacanciesMethod = vacancy.getGetVacanciesMethod();
        switch (getVacanciesMethod) {
            case GET_ALL_VACANCIES_BY_REQUIREMENTS:
                result = getAllVacanciesByRequirements(requirementListList);
                break;
            case GET_ONE_AT_LEAST_VACANCIES_BY_REQUIREMENTS:
                result = getVacanciesByAtLeastRequirement(requirementListList);
                break;
            case GET_ALL_VACANCIES_BY_REQUIREMENTS_AND_LEVEL: {
                List<Requirement> upperRequirements = vacancy.getUpperRequirements();
                List<Requirement> lowerRequirements = requirementListList;
                result = getAllVacanciesByRequirementsAndLevel(lowerRequirements, upperRequirements);
                break;
            }
        } return result;
    }

    public void clearDataBase() {
        mapOfSkillAndEmployee.clear();
        mapOfVacancyAndRequirements.clear();
        mapVacancies.clear();
        mapUsers.clear();
        userSession.clear();
    }
}














