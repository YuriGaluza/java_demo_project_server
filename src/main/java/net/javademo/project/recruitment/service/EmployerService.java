package net.javademo.project.recruitment.service;

import com.google.gson.Gson;
import net.javademo.project.recruitment.dao.EmployerDao;
import net.javademo.project.recruitment.daoimpl.EmployerDaoImpl;
import net.javademo.project.recruitment.model.*;
import net.javademo.project.recruitment.request.*;
import net.javademo.project.recruitment.response.*;
import net.javademo.project.recruitment.server.ServerErrorCode;
import net.javademo.project.recruitment.server.ServerException;

import java.util.*;

public class EmployerService {

    private EmployerDao employerDaoImpl = new EmployerDaoImpl();
    private Gson gson = new Gson();
    private static final int PASSWORD_LENGTH = 6;
    private static int LOGIN_LENGTH = 4;

    public String registerEmployer(String json) {
        String jsonResponse;
        RegisterEmployerDtoRequest registerEmployerDtoRequest = gson.fromJson(json, RegisterEmployerDtoRequest.class);
        try {
            validate(registerEmployerDtoRequest);
            String token = UUID.randomUUID().toString();
            Employer employer = new Employer(registerEmployerDtoRequest.getFirstName(),
                    registerEmployerDtoRequest.getLastName(),
                    registerEmployerDtoRequest.getMiddleName(),
                    registerEmployerDtoRequest.getLogin(),
                    registerEmployerDtoRequest.getPassword(),
                    registerEmployerDtoRequest.getEmail(),
                    registerEmployerDtoRequest.getCompany(),
                    registerEmployerDtoRequest.getAddress());
            employerDaoImpl.insert(employer);
            employerDaoImpl.userLogin(token, employer);
            jsonResponse = gson.toJson(new RegisterEmployerDtoResponse(token));
        } catch (ServerException e) {
            return jsonResponse = gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        } return jsonResponse;
    }

    private static boolean validate(RegisterEmployerDtoRequest registerEmployerDtoRequest) throws ServerException {
        if (registerEmployerDtoRequest.getFirstName().equals(null) || registerEmployerDtoRequest.getFirstName().equals(""))
            throw new ServerException(ServerErrorCode.WRONG_FIRST_NAME);
        if (registerEmployerDtoRequest.getLastName().equals(null) || registerEmployerDtoRequest.getLastName().equals(""))
            throw new ServerException(ServerErrorCode.WRONG_LAST_NAME);
        if (registerEmployerDtoRequest.getLogin().length() < LOGIN_LENGTH)
            throw new ServerException(ServerErrorCode.WRONG_LOGIN_LENGTH);
        if (registerEmployerDtoRequest.getLogin().equals(null) || registerEmployerDtoRequest.getLogin().equals(""))
            throw new ServerException(ServerErrorCode.LOGIN_IS_EMPTY);
        if (registerEmployerDtoRequest.getPassword().length() < PASSWORD_LENGTH)
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD_LENGTH);
        return true;
    }

    public String addVacancy(String json) throws ServerException {
        AddVacancyDtoRequest addVacancyDtoRequest = gson.fromJson(json, AddVacancyDtoRequest.class);
        String token = addVacancyDtoRequest.getToken();
        List<Requirement> requirementList = new LinkedList<>();
        for (RequirementDtoRequest elem : addVacancyDtoRequest.getRequirements()) {
            requirementList.add(new Requirement(elem.getName(),elem.getLevel(), elem.isMandatory()));
        }
        try {
            employerDaoImpl.checkUserSession(token);
            Vacancy vacancy = new Vacancy(addVacancyDtoRequest.getName(), addVacancyDtoRequest.getCompany(),
                    addVacancyDtoRequest.getSalary(), requirementList, addVacancyDtoRequest.isActive());
            employerDaoImpl.addVacancy(vacancy, employerDaoImpl.getEmployerByToken(token));
        } catch (ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        } return "{}";
    }

    public String getAllVacanciesForEmployer(String json) {
        GetAllVacanciesDtoRequest getAllVacanciesDtoRequest = gson.fromJson(json, GetAllVacanciesDtoRequest.class);
        String token = getAllVacanciesDtoRequest.getToken();
        try {
            if (employerDaoImpl.getEmployerByToken(token) instanceof Employer) {
                Collection<Vacancy> setVacancies = employerDaoImpl.getAllVacancies();
                return gson.toJson(setVacancies, GetAllVacanciesDtoRequest.class);
            } else throw new ServerException(ServerErrorCode.WRONG_TOKEN);
        } catch (ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        }
    }

    public String userLogout(String json) {
        UserLogoutDtoRequest userLogoutDtoRequest = gson.fromJson(json, UserLogoutDtoRequest.class);
        try {
            employerDaoImpl.userLogout(userLogoutDtoRequest.getToken());
        } catch (NullPointerException | ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.toString()));
        } return "{}";
    }

    public String employerLogin(String json) {
        LoginUserDtoRequest loginUserDtoRequest = gson.fromJson(json, LoginUserDtoRequest.class);
        String token = UUID.randomUUID().toString();
        User user = employerDaoImpl.getEmployerByLogin(loginUserDtoRequest.getLogin());
        try {
            if (!employerDaoImpl.checkEmployerLogin(loginUserDtoRequest.getLogin()))
                throw new ServerException(ServerErrorCode.EMPLOYER_IS_NOT_FOUND);
            employerDaoImpl.userLogin(token, user);
        } catch (ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        } return gson.toJson(new RegisterEmployerDtoResponse(token));
    }

    public String getEmployeesByVacancy(String json) {
        GetEmployeesByVacancyDtoRequest getEmployeesByVacancyDtoRequest = gson.fromJson(json, GetEmployeesByVacancyDtoRequest.class);
        String token = getEmployeesByVacancyDtoRequest.getToken();
        Set<EmployeeDtoRequest> employeeSet1 = new HashSet<>();
        List<Requirement> requirementList = new LinkedList<>();
        List<Requirement> upperRequirementList = new LinkedList<>();
        Vacancy vacancy;
        try {
            employerDaoImpl.checkUserSession(token);
            for (RequirementDtoRequest elem : getEmployeesByVacancyDtoRequest.getVacancy().getRequirements()) {
                requirementList.add(new Requirement(elem.getName(), elem.getLevel(), elem.isMandatory()));
            }
            for (RequirementDtoRequest elem : getEmployeesByVacancyDtoRequest.getVacancy().getUpperRequirements()) {
                upperRequirementList.add(new Requirement(elem.getName(), elem.getLevel(), elem.isMandatory()));
            }
            vacancy = new Vacancy(getEmployeesByVacancyDtoRequest.getVacancy().getName(), getEmployeesByVacancyDtoRequest.getVacancy().getCompany(),
                    getEmployeesByVacancyDtoRequest.getVacancy().getSalary(), requirementList,
                    getEmployeesByVacancyDtoRequest.getVacancy().isActive());
            vacancy.setGetEmployeesMethod(GetEmployeesMethod.valueOf(getEmployeesByVacancyDtoRequest.getVacancy().getGetEmployeesMethod()));
            vacancy.setUpperRequirements(upperRequirementList);
            for (Employee employee : employerDaoImpl.getEmployeesByVacancy(vacancy)) {
                List<SkillDtoRequest> skillList = new LinkedList<>();
                for (Skill skill : employee.getSkills()) {
                    skillList.add(new SkillDtoRequest(skill.getName(), skill.getLevel()));
                }
                employeeSet1.add(new EmployeeDtoRequest(employee.getFirstName(), employee.getLastName(), employee.getMiddleName(),
                        employee.getEmail(), skillList));
            }
            GetEmployeesByVacancyDtoResponse getEmployeesByVacancyDtoResponse = new GetEmployeesByVacancyDtoResponse(employeeSet1);
            return gson.toJson(getEmployeesByVacancyDtoResponse);
        } catch (ServerException | IllegalArgumentException e) {
            return "{}";
        }
    }

    public String getVacancyForEmployer(String json) {
        GetVacancyForEmployerDtoRequest getVacancyForEmployerDtoRequest = gson.fromJson(json, GetVacancyForEmployerDtoRequest.class);
        String token = getVacancyForEmployerDtoRequest.getToken();
        Set<VacancyDtoResponse> vacancyDtoRequestSet = new LinkedHashSet<>();
        try {
            employerDaoImpl.checkUserSession(token);
            for (Vacancy vacancy : employerDaoImpl.getVacancyForEmployer(employerDaoImpl.getEmployerByToken(token))) {
                vacancyDtoRequestSet.add(new VacancyDtoResponse(vacancy.getName(), vacancy.getCompany(),
                        vacancy.getSalary()));
            }
            GetVacanciesByVacancyDtoResponse getVacanciesByRequirementsResponse =
                    new GetVacanciesByVacancyDtoResponse(vacancyDtoRequestSet);
            return gson.toJson(getVacanciesByRequirementsResponse);
        } catch (ServerException e) {
            return "{}";
        }
    }
}
