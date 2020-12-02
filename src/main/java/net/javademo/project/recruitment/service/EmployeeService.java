package net.javademo.project.recruitment.service;

import com.google.gson.Gson;
import net.javademo.project.recruitment.dao.EmployeeDao;
import net.javademo.project.recruitment.daoimpl.EmployeeDaoImpl;
import net.javademo.project.recruitment.model.*;
import net.javademo.project.recruitment.request.*;
import net.javademo.project.recruitment.response.ErrorDtoResponse;
import net.javademo.project.recruitment.response.GetVacanciesByVacancyDtoResponse;
import net.javademo.project.recruitment.response.RegisterEmployeeDtoResponse;
import net.javademo.project.recruitment.response.VacancyDtoResponse;
import net.javademo.project.recruitment.server.ServerErrorCode;
import net.javademo.project.recruitment.server.ServerException;

import java.util.*;

public class EmployeeService {

    private EmployeeDao employeeDaoImpl = new EmployeeDaoImpl();
    private Gson gson = new Gson();
    private static final int PASSWORD_LENGTH = 6;
    private static final int LOGIN_LENGTH = 4;

    public String registerEmployee(String json) {
        String jsonResponse;
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest = gson.fromJson(json, RegisterEmployeeDtoRequest.class);
        try {
            validate(registerEmployeeDtoRequest);
            String token = UUID.randomUUID().toString();
            Employee employee = new Employee(registerEmployeeDtoRequest.getFirstName(),
                    registerEmployeeDtoRequest.getLastName(), registerEmployeeDtoRequest.getMiddleName(),
                    registerEmployeeDtoRequest.getLogin(), registerEmployeeDtoRequest.getPassword(),
                    registerEmployeeDtoRequest.getEmail(), registerEmployeeDtoRequest.getSkills());
            employeeDaoImpl.insert(employee);
            employeeDaoImpl.userLogin(token, employee);
            jsonResponse = gson.toJson(new RegisterEmployeeDtoResponse(token));
        } catch (ServerException e) {
            return jsonResponse = gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        } return jsonResponse;
    }

    private static boolean validate(RegisterEmployeeDtoRequest registerEmployeeDtoRequest) throws ServerException {
        if (registerEmployeeDtoRequest.getFirstName().equals(null) || registerEmployeeDtoRequest.getFirstName().equals(""))
            throw new ServerException(ServerErrorCode.WRONG_FIRST_NAME);
        if (registerEmployeeDtoRequest.getLastName().equals(null) || registerEmployeeDtoRequest.getLastName().equals(""))
            throw new ServerException(ServerErrorCode.WRONG_LAST_NAME);
        if (registerEmployeeDtoRequest.getLogin().length() < LOGIN_LENGTH)
            throw new ServerException(ServerErrorCode.WRONG_LOGIN_LENGTH);
        if (registerEmployeeDtoRequest.getLogin().equals(null) || registerEmployeeDtoRequest.getLogin().equals(""))
            throw new ServerException(ServerErrorCode.LOGIN_IS_EMPTY);
        if (registerEmployeeDtoRequest.getPassword().length() < PASSWORD_LENGTH)
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD_LENGTH);
        return true;
    }

    public String getAllVacanciesForEmployee(String json) {
        GetAllVacanciesDtoRequest getAllVacanciesDtoRequest = gson.fromJson(json, GetAllVacanciesDtoRequest.class);
        String token = getAllVacanciesDtoRequest.getToken();
        try {
            if (!employeeDaoImpl.checkUserToken(token))
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            Collection<Vacancy> setVacancies = employeeDaoImpl.getAllVacancies();
            return gson.toJson(setVacancies, GetAllVacanciesDtoRequest.class);
        } catch (ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        }
    }

    public String insertSkill(String json) {
        InsertSkillDtoRequest insertSkillDtoRequest = gson.fromJson(json, InsertSkillDtoRequest.class);
        String token = insertSkillDtoRequest.getToken();
        try {
            employeeDaoImpl.checkUserSession(token);
            Skill skill = new Skill(insertSkillDtoRequest.getName(), insertSkillDtoRequest.getLevel());
            employeeDaoImpl.insertSkill(skill, employeeDaoImpl.getEmployeeByToken(token));
        } catch (ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        } return "{}";
    }

    public String addSkill(String json) {
        AddSkillDtoRequest addSkillDtoRequest = gson.fromJson(json, AddSkillDtoRequest.class);
        String token = addSkillDtoRequest.getToken();
        try {
            employeeDaoImpl.checkUserSession(token);
            Skill skill = new Skill(addSkillDtoRequest.getName(), addSkillDtoRequest.getLevel());
            employeeDaoImpl.addSkill(skill, employeeDaoImpl.getEmployeeByToken(token));
        } catch (ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        } return "{}";
    }

    public String employeeLogout(String json) {
        UserLogoutDtoRequest userLogoutDtoRequest = gson.fromJson(json, UserLogoutDtoRequest.class);
        try {
            employeeDaoImpl.userLogout(userLogoutDtoRequest.getToken());
        } catch (NullPointerException | ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.toString()));
        } return "{}";
    }

    public String userLogin(String json) {
        LoginUserDtoRequest loginUserDtoRequest = gson.fromJson(json, LoginUserDtoRequest.class);
        String token = UUID.randomUUID().toString();
        User user = employeeDaoImpl.getEmployeeByLogin(loginUserDtoRequest.getLogin());
        try {
            if (!employeeDaoImpl.checkEmployeeLogin(loginUserDtoRequest.getLogin()))
                throw new ServerException(ServerErrorCode.WRONG_LOGIN);
            employeeDaoImpl.userLogin(token, user);
        } catch (ServerException e) {
            return gson.toJson(new ErrorDtoResponse(e.getServerErrorCode().toString()));
        } return gson.toJson(new RegisterEmployeeDtoResponse(token));
    }

    public String getVacanciesByVacancy(String json) {
        GetVacanciesByVacancyDtoRequest getVacanciesByVacancyDtoRequest = gson.fromJson(json, GetVacanciesByVacancyDtoRequest.class);
        String token = getVacanciesByVacancyDtoRequest.getToken();
        Set<VacancyDtoResponse> vacancySet = new HashSet<>();
        List<Requirement> requirementList = new LinkedList<>();
        List<Requirement> upperRequirementList = new LinkedList<>();
        Vacancy vacancy;
        try {
            employeeDaoImpl.checkUserSession(token);
            for (RequirementDtoRequest elem : getVacanciesByVacancyDtoRequest.getVacancy().getRequirements()) {
                requirementList.add(new Requirement(elem.getName(), elem.getLevel(), elem.isMandatory()));
            }
            for (RequirementDtoRequest elem : getVacanciesByVacancyDtoRequest.getVacancy().getUpperRequirements()) {
                upperRequirementList.add(new Requirement(elem.getName(), elem.getLevel(), elem.isMandatory()));
            }
            vacancy = new Vacancy(getVacanciesByVacancyDtoRequest.getVacancy().getName(), getVacanciesByVacancyDtoRequest.getVacancy().getCompany(),
                    getVacanciesByVacancyDtoRequest.getVacancy().getSalary(), requirementList,
                    getVacanciesByVacancyDtoRequest.getVacancy().isActive());
            vacancy.setGetVacanciesMethod(GetVacanciesMethod.valueOf(getVacanciesByVacancyDtoRequest.getVacancy().getGetVacanciesMethod()));
            vacancy.setUpperRequirements(upperRequirementList);
            for (Vacancy vacancy1 : employeeDaoImpl.getVacanciesByVacancy(vacancy)) {
                vacancySet.add(new VacancyDtoResponse(vacancy1.getName(), vacancy1.getCompany(), vacancy1.getSalary()));
            }
            GetVacanciesByVacancyDtoResponse getVacanciesByVacancyResponse =
                    new GetVacanciesByVacancyDtoResponse(vacancySet);
            return gson.toJson(getVacanciesByVacancyResponse);
        } catch (ServerException | IllegalArgumentException e) {
            return "{}";
        }
    }
}
