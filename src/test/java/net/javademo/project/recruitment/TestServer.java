package net.javademo.project.recruitment;

import com.google.gson.Gson;
import net.javademo.project.recruitment.request.*;
import net.javademo.project.recruitment.request.VacancyDtoRequest;
import net.javademo.project.recruitment.response.*;
import net.javademo.project.recruitment.server.Server;
import net.javademo.project.recruitment.server.ServerException;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestServer {
    private Gson gson = new Gson();
    private Server server = new Server();

    public void clearDataBase() {
        server.clearDataBase();
    }


    @Test
        public void testScenarioEmployerAndEmployee() throws ServerException {

        clearDataBase();

        List<RequirementDtoRequest> requirementDtoRequestsQuentin = new LinkedList<>();//Список требований вакансии Quentin
        requirementDtoRequestsQuentin.add(new RequirementDtoRequest("C", 3, true));
        requirementDtoRequestsQuentin.add(new RequirementDtoRequest("Java", 3, true));

        List<RequirementDtoRequest> upperRequirementsList = new LinkedList<>(); //Создание пустого списка требований по верхнему уровню
        upperRequirementsList.add(new RequirementDtoRequest("C", 5, true));
        upperRequirementsList.add(new RequirementDtoRequest("Java", 5, true));

        VacancyDtoRequest vacancyQuentin = new VacancyDtoRequest("Developer", "General electric",
                50, requirementDtoRequestsQuentin, true);
        vacancyQuentin.setGetEmployeesMethod("GET_EMPLOYEES_ALL_SKILLS"); //Установление метода поиска
        vacancyQuentin.setGetVacanciesMethod("GET_ALL_VACANCIES_BY_REQUIREMENTS"); //Установление метода поиска
        vacancyQuentin.setUpperRequirements(upperRequirementsList); //Установление списка требований по верхнему уровню

        RegisterEmployeeDtoRequest registerAlexDtoRequest = new RegisterEmployeeDtoRequest("Alex",
                "Merser", "", "alexm", "skwjw2q", "alexm@yandex.ru", new LinkedList());
        RegisterEmployeeDtoRequest registerRobertDtoRequest = new RegisterEmployeeDtoRequest("Robert",
                "Tyler", "", "bobtyler", "fdfee33fr", "bobtyler@mail.ru", new LinkedList());
        RegisterEmployeeDtoRequest registerRickDtoRequest = new RegisterEmployeeDtoRequest("Rick",
                "Grimes", "", "ricksgrimes", "fdd3rds3", "rickgrimes@yahoo.com", new LinkedList());
        RegisterEmployeeDtoRequest registerTonyDtoRequest = new RegisterEmployeeDtoRequest("Anthony",
                "Stark", "", "tonystark", "4fdf33f", "tonystark54@gmail.com", new LinkedList());
        RegisterEmployerDtoRequest registerQuentinDtoRequest = new RegisterEmployerDtoRequest("Quentin", "Burk",
                "", "quentinburk", "dsdweddd3", "quentinburk87@mail.ru", "General electric", "Manhattan, lost avenue 5");
        RegisterEmployerDtoRequest registerFarrukhDtoRequest = new RegisterEmployerDtoRequest("Farrukh", "Bulsara",
                "", "farfukhbulsara", "jdynenjw4", "farrukhbul@yandex.ru", "Borman bros.", "Denver, Washington street 43");
        RegisterEmployerDtoRequest registerJamesDtoRequest = new RegisterEmployerDtoRequest("James", "Greenwood",
                "", "littleRagamuffin", "ber4343fd", "greenwoodjim@gmail.com", "Workhouse", "London, Oxford Street 13");
        RegisterEmployerDtoRequest registerIanDtoRequest = new RegisterEmployerDtoRequest("Ian", "Gillan",
                "", "deepGillan", "kyhgr46g", "iangillan75@yandex.ru", "Deep Purple", "London, Hounslow");

        String requestAlex = gson.toJson(registerAlexDtoRequest);//Employee Alex
        String requestBob = gson.toJson(registerRobertDtoRequest);//Employee Bob
        String requestRick = gson.toJson(registerRickDtoRequest);//Employee Rick
        String requestTony = gson.toJson(registerTonyDtoRequest);//Employee Tony
        String requestQuentin = gson.toJson(registerQuentinDtoRequest);//Employer Quentin
        String requestFarrukh = gson.toJson(registerFarrukhDtoRequest);//Employer Farrukh
        String requestJames = gson.toJson(registerJamesDtoRequest);//Employer James
        String requestIan = gson.toJson(registerIanDtoRequest);//Employer Ian

        //Что приходит от сервера
        String jsonAlexResponse = server.registerEmployee(requestAlex);//Registration Alex
        String jsonBobResponse = server.registerEmployee(requestBob);//Registration Bob
        String jsonRickResponse = server.registerEmployee(requestRick);//Registration Rick
        String jsonTonyResponse = server.registerEmployee(requestTony);//Registration Tony
        String jsonQuentinResponse = server.registerEmployer(requestQuentin);//Registration Quentin
        String jsonFarrukhResponse = server.registerEmployer(requestFarrukh);//Registration Farrukh
        String jsonJamesResponse = server.registerEmployer(requestJames);//Registration James
        String jsonIanResponse = server.registerEmployer(requestIan);//Registration Ian

        //То, что мы ожидаем от сервера
        RegisterEmployeeDtoResponse registerAlexDtoResponse = gson.fromJson(jsonAlexResponse, RegisterEmployeeDtoResponse.class);
        RegisterEmployeeDtoResponse registerBobDtoResponse = gson.fromJson(jsonBobResponse, RegisterEmployeeDtoResponse.class);
        RegisterEmployeeDtoResponse registerRickDtoResponse = gson.fromJson(jsonRickResponse, RegisterEmployeeDtoResponse.class);
        RegisterEmployeeDtoResponse registerTonyDtoResponse = gson.fromJson(jsonTonyResponse, RegisterEmployeeDtoResponse.class);
        RegisterEmployerDtoResponse registerQuentinDtoResponse = gson.fromJson(jsonQuentinResponse, RegisterEmployerDtoResponse.class);
        RegisterEmployerDtoResponse registerFarrukhDtoResponse = gson.fromJson(jsonFarrukhResponse, RegisterEmployerDtoResponse.class);
        RegisterEmployerDtoResponse registerJamesDtoResponse = gson.fromJson(jsonJamesResponse, RegisterEmployerDtoResponse.class);
        RegisterEmployerDtoResponse registerIanDtoResponse = gson.fromJson(jsonIanResponse, RegisterEmployerDtoResponse.class);

        assertAll(() -> assertEquals(gson.toJson(registerAlexDtoResponse), jsonAlexResponse),//Регистрация прошла успешно
                  () -> assertEquals(gson.toJson(registerBobDtoResponse), jsonBobResponse),
                  () -> assertEquals(gson.toJson(registerRickDtoResponse), jsonRickResponse),
                  () -> assertEquals(gson.toJson(registerTonyDtoResponse), jsonTonyResponse),
                  () -> assertEquals(gson.toJson(registerQuentinDtoResponse), jsonQuentinResponse),
                  () -> assertEquals(gson.toJson(registerFarrukhDtoResponse), jsonFarrukhResponse),
                  () -> assertEquals(gson.toJson(registerJamesDtoResponse), jsonJamesResponse),
                  () -> assertEquals(gson.toJson(registerIanDtoResponse), jsonIanResponse));

        InsertSkillDtoRequest insertAlexSkillDtoRequest1 =
                new InsertSkillDtoRequest("C", 2, registerAlexDtoResponse.getToken());
        InsertSkillDtoRequest insertAlexSkillDtoRequest2 =
                new InsertSkillDtoRequest("Java", 3, registerAlexDtoResponse.getToken());
        InsertSkillDtoRequest insertBobSkillDtoRequest1 =
                new InsertSkillDtoRequest("C", 3, registerBobDtoResponse.getToken());
        InsertSkillDtoRequest insertBobSkillDtoRequest2 =
                new InsertSkillDtoRequest("Java", 3, registerBobDtoResponse.getToken());
        InsertSkillDtoRequest insertRickSkillDtoRequest1 =
                new InsertSkillDtoRequest("C", 3, registerRickDtoResponse.getToken());
        InsertSkillDtoRequest insertRickSkillDtoRequest2 =
                new InsertSkillDtoRequest("Java", 3, registerRickDtoResponse.getToken());
        InsertSkillDtoRequest insertTonySkillDtoRequest1 =
                new InsertSkillDtoRequest("C", 5, registerTonyDtoResponse.getToken());
        InsertSkillDtoRequest insertTonySkillDtoRequest2 =
                new InsertSkillDtoRequest("Java", 5, registerTonyDtoResponse.getToken());

        String jsonAlexInsertSkill1 = gson.toJson(insertAlexSkillDtoRequest1);
        String jsonAlexInsertSkill2 = gson.toJson(insertAlexSkillDtoRequest2);
        String jsonBobInsertSkill1 = gson.toJson(insertBobSkillDtoRequest1);
        String jsonBobInsertSkill2 = gson.toJson(insertBobSkillDtoRequest2);
        String jsonRickInsertSkill1 = gson.toJson(insertRickSkillDtoRequest1);
        String jsonRickInsertSkill2 = gson.toJson(insertRickSkillDtoRequest2);
        String jsonTonyInsertSkill1 = gson.toJson(insertTonySkillDtoRequest1);
        String jsonTonyInsertSkill2 = gson.toJson(insertTonySkillDtoRequest2);

        String jsonAlexInsertSkill1Response = server.insertSkill(jsonAlexInsertSkill1);//Добавляем скиллы Employees
        String jsonAlexInsertSkill2Response = server.insertSkill(jsonAlexInsertSkill2);
        String jsonBobInsertSkill1Response = server.insertSkill(jsonBobInsertSkill1);
        String jsonBobInsertSkill2Response = server.insertSkill(jsonBobInsertSkill2);
        String jsonRickInsertSkill1Response = server.insertSkill(jsonRickInsertSkill1);
        String jsonRickInsertSkill2Response = server.insertSkill(jsonRickInsertSkill2);
        String jsonTonyInsertSkill1Response = server.insertSkill(jsonTonyInsertSkill1);
        String jsonTonyInsertSkill2Response = server.insertSkill(jsonTonyInsertSkill2);

        assertAll(() -> assertEquals("{}", jsonAlexInsertSkill1Response),//Добавление скиллов прошло успешно
                  () -> assertEquals("{}", jsonAlexInsertSkill2Response),
                  () -> assertEquals("{}", jsonBobInsertSkill1Response),
                  () -> assertEquals("{}", jsonBobInsertSkill2Response),
                  () -> assertEquals("{}", jsonRickInsertSkill1Response),
                  () -> assertEquals("{}", jsonRickInsertSkill2Response),
                  () -> assertEquals("{}", jsonTonyInsertSkill1Response),
                  () -> assertEquals("{}", jsonTonyInsertSkill2Response));

        RequirementsListDtoRequest requirementsListDtoRequestQuentin = new RequirementsListDtoRequest(new LinkedList<>());
        requirementsListDtoRequestQuentin.getRequirementDtoRequestList().add(new RequirementDtoRequest("C", 3, true));
        requirementsListDtoRequestQuentin.getRequirementDtoRequestList().add(new RequirementDtoRequest("Java", 3, true));

        RequirementsListDtoRequest requirementsListDtoRequestFarrukh = new RequirementsListDtoRequest(new LinkedList<>());
        requirementsListDtoRequestFarrukh.getRequirementDtoRequestList().add(new RequirementDtoRequest("C", 3, true));
        requirementsListDtoRequestFarrukh.getRequirementDtoRequestList().add(new RequirementDtoRequest("Java", 3, true));

        RequirementsListDtoRequest requirementsListDtoRequestJames = new RequirementsListDtoRequest(new LinkedList<>());
        requirementsListDtoRequestJames.getRequirementDtoRequestList().add(new RequirementDtoRequest("C", 1, true));
        requirementsListDtoRequestJames.getRequirementDtoRequestList().add(new RequirementDtoRequest("Java", 1, true));

        RequirementsListDtoRequest requirementsListDtoRequestIan = new RequirementsListDtoRequest(new LinkedList<>());
        requirementsListDtoRequestIan.getRequirementDtoRequestList().add(new RequirementDtoRequest("C", 3, true));
        requirementsListDtoRequestIan.getRequirementDtoRequestList().add(new RequirementDtoRequest("Java", 5, true));

        AddVacancyDtoRequest addVacancyDtoRequestVacancyQuentin1 = new AddVacancyDtoRequest("Junior Developer",
                "General electric", 50, requirementsListDtoRequestQuentin.getRequirementDtoRequestList(),
                true, registerQuentinDtoResponse.getToken());
        AddVacancyDtoRequest addVacancyDtoRequestVacancyFarrukh = new AddVacancyDtoRequest("Middle Developer",
                "Borman bros.", 30, requirementsListDtoRequestFarrukh.getRequirementDtoRequestList(),
                true, registerFarrukhDtoResponse.getToken());
        AddVacancyDtoRequest addVacancyDtoRequestVacancyJames = new AddVacancyDtoRequest("Senior Developer",
                "Workhouse", 60, requirementsListDtoRequestJames.getRequirementDtoRequestList(),
                true, registerJamesDtoResponse.getToken());
        AddVacancyDtoRequest addVacancyDtoRequestVacancyIan = new AddVacancyDtoRequest("Program Developer",
                "Deep Purple", 40, requirementsListDtoRequestIan.getRequirementDtoRequestList(),
                true, registerIanDtoResponse.getToken());

        String stringVacancyQuentin1 = server.addVacancy(gson.toJson(addVacancyDtoRequestVacancyQuentin1));
        String stringVacancyFarrukh = server.addVacancy(gson.toJson(addVacancyDtoRequestVacancyFarrukh));
        String stringVacancyJames = server.addVacancy(gson.toJson(addVacancyDtoRequestVacancyJames));
        String stringVacancyIan = server.addVacancy(gson.toJson(addVacancyDtoRequestVacancyIan));

        assertAll(() -> assertEquals("{}", stringVacancyQuentin1), //Добавление вакансий прошло успешно
                  () -> assertEquals("{}", stringVacancyFarrukh),
                  () -> assertEquals("{}", stringVacancyJames),
                  () -> assertEquals("{}", stringVacancyIan));

        List<SkillDtoRequest> skillDtoRequestListAlex = new LinkedList<>();
        skillDtoRequestListAlex.add(new SkillDtoRequest("C", 2));
        skillDtoRequestListAlex.add(new SkillDtoRequest("Java", 3));

        List<SkillDtoRequest> skillDtoRequestListBob = new LinkedList<>();
        skillDtoRequestListBob.add(new SkillDtoRequest("C", 3));
        skillDtoRequestListBob.add(new SkillDtoRequest("Java", 3));

        List<SkillDtoRequest> skillDtoRequestListRick = new LinkedList<>();
        skillDtoRequestListRick.add(new SkillDtoRequest("C", 3));
        skillDtoRequestListRick.add(new SkillDtoRequest("Java", 3));

        List<SkillDtoRequest> skillDtoRequestListTony = new LinkedList<>();
        skillDtoRequestListTony.add(new SkillDtoRequest("C", 4));
        skillDtoRequestListTony.add(new SkillDtoRequest("Java", 5));

        GetEmployeesByVacancyDtoRequest getEmployeesByVacancyDtoRequest1 = //Создание запроса серверу
                new GetEmployeesByVacancyDtoRequest(registerQuentinDtoResponse.getToken(), vacancyQuentin);
        String employee1 = gson.toJson(getEmployeesByVacancyDtoRequest1); //Перевод в json запроса

        String responseEmployee1 = server.getEmployeesByVacancy(employee1);//Отправка запроса серверу и получение от него ответа
        GetEmployeesByVacancyDtoResponse response1 = //Ответ от сервера Set<Employee>
                gson.fromJson(responseEmployee1, GetEmployeesByVacancyDtoResponse.class); //Преобразование из json в response

        Set<String> firstNamesSet1 = new HashSet<>();
        Set<String> lastNamesSet1 = new HashSet<>();
        for (EmployeeDtoRequest elem : response1.getEmployeeSet()) {
            firstNamesSet1.add(elem.getFirstName());
            lastNamesSet1.add(elem.getLastName());
        }

        //Проверяем содержание списка Employees
        assertAll(() -> assertEquals(false, firstNamesSet1.contains("Alex")),
                  () -> assertEquals(true, firstNamesSet1.contains("Robert")),
                  () -> assertEquals(true, firstNamesSet1.contains("Rick")),
                  () -> assertEquals(false, firstNamesSet1.contains("Tony")));

        assertAll(() -> assertEquals(false, lastNamesSet1.contains("Merser")),
                  () -> assertEquals(true, lastNamesSet1.contains("Tyler")),
                  () -> assertEquals(true, lastNamesSet1.contains("Grimes")),
                  () -> assertEquals(false, lastNamesSet1.contains("Stark")));

        vacancyQuentin.setGetEmployeesMethod("GET_EMPLOYEES_BY_AT_LEAST_SKILL");//Установка метода поиска

        GetEmployeesByVacancyDtoRequest getEmployeesByVacancyDtoRequest2 = //Создание запроса серверу
                new GetEmployeesByVacancyDtoRequest(registerQuentinDtoResponse.getToken(), vacancyQuentin);
        String employee2 = gson.toJson(getEmployeesByVacancyDtoRequest2); //Перевод в json запроса

        String responseEmployee2 = server.getEmployeesByVacancy(employee2);//Отправка запроса серверу и получение от него ответа
        GetEmployeesByVacancyDtoResponse response2 = //Ответ от сервера Set<Employee>
                gson.fromJson(responseEmployee2, GetEmployeesByVacancyDtoResponse.class); //Преобразование из json в response

        Set<String> firstNamesSet2 = new HashSet<>();
        Set<String> lastNamesSet2 = new HashSet<>();
        for (EmployeeDtoRequest elem : response2.getEmployeeSet()) {
            firstNamesSet2.add(elem.getFirstName());
            lastNamesSet2.add(elem.getLastName());
        }

        //Проверяем содержание списка Employees
        assertAll(() -> assertEquals(true, firstNamesSet2.contains("Alex")),
                  () -> assertEquals(true, firstNamesSet2.contains("Robert")),
                  () -> assertEquals(true, firstNamesSet2.contains("Rick")),
                  () -> assertEquals(false, firstNamesSet2.contains("Tony")));

        assertAll(() -> assertEquals(true, lastNamesSet2.contains("Merser")),
                  () -> assertEquals(true, lastNamesSet2.contains("Tyler")),
                  () -> assertEquals(true, lastNamesSet2.contains("Grimes")),
                  () -> assertEquals(false, lastNamesSet2.contains("Stark")));

        vacancyQuentin.setGetEmployeesMethod("GET_ALL_EMPLOYEES_BY_SKILL_AND_LEVEL");//Установка метода поиска

        GetEmployeesByVacancyDtoRequest getEmployeesByVacancyDtoRequest3 = //Создание запроса серверу
                new GetEmployeesByVacancyDtoRequest(registerQuentinDtoResponse.getToken(), vacancyQuentin);
        String employee3 = gson.toJson(getEmployeesByVacancyDtoRequest3); //Перевод в json запроса

        String responseEmployee3 = server.getEmployeesByVacancy(employee3);//Отправка запроса серверу и получение от него ответа
        GetEmployeesByVacancyDtoResponse response3 = //Ответ от сервера Set<Employee>
                gson.fromJson(responseEmployee3, GetEmployeesByVacancyDtoResponse.class); //Преобразование из json в response

        Set<String> firstNamesSet3 = new HashSet<>();
        Set<String> lastNamesSet3 = new HashSet<>();
        for (EmployeeDtoRequest elem : response3.getEmployeeSet()) {
            firstNamesSet3.add(elem.getFirstName());
            lastNamesSet3.add(elem.getLastName());
        }

        //Проверяем содержание списка Employees
        assertAll(() -> assertEquals(true, firstNamesSet3.contains("Alex")),
                 () -> assertEquals(true, firstNamesSet3.contains("Robert")),
                 () -> assertEquals(true, firstNamesSet3.contains("Rick")),
                 () -> assertEquals(false, firstNamesSet3.contains("Tony")));

        assertAll(() -> assertEquals(true, lastNamesSet3.contains("Merser")),
                  () -> assertEquals(true, lastNamesSet3.contains("Tyler")),
                  () -> assertEquals(true, lastNamesSet3.contains("Grimes")),
                  () -> assertEquals(true, lastNamesSet3.contains("Stark")));

        //Проверка метода поиска вакансий от Employee Alex
        GetVacanciesByVacancyDtoRequest getVacanciesByVacancyDtoRequest1 = //Создание запроса серверу
                new GetVacanciesByVacancyDtoRequest(registerAlexDtoResponse.getToken(), vacancyQuentin);
        String vacancy1 = gson.toJson(getVacanciesByVacancyDtoRequest1); //Перевод в json запроса

        String responseVacancy1 = server.getVacanciesByVacancy(vacancy1);//Отправка запроса серверу и получение от него ответа
        GetVacanciesByVacancyDtoResponse responseVacancies1 = //Ответ от сервера Set<Employee>
                gson.fromJson(responseVacancy1, GetVacanciesByVacancyDtoResponse.class); //Преобразование из json в response

        Set<String> NameVacancySet1 = new HashSet<>();
        Set<String> CompanyVacancySet1 = new HashSet<>();
        Set<Integer> SalaryVacancySet1 = new HashSet<>();

        for (VacancyDtoResponse vacancy : responseVacancies1.getVacancySet()) {
            NameVacancySet1.add(vacancy.getName());
            CompanyVacancySet1.add(vacancy.getCompany());
            SalaryVacancySet1.add(vacancy.getSalary());
        }
        //Проверяем содержание списка VacancySet
        assertAll(() -> assertEquals(true, NameVacancySet1.contains("Junior Developer")),
                  () -> assertEquals(true, NameVacancySet1.contains("Middle Developer")),
                  () -> assertEquals(false, NameVacancySet1.contains("Senior Developer")),
                  () -> assertEquals(false, NameVacancySet1.contains("Program Developer")));

        assertAll(() -> assertEquals(true, CompanyVacancySet1.contains("General electric")),
                  () -> assertEquals(true, CompanyVacancySet1.contains("Borman bros.")),
                  () -> assertEquals(false, CompanyVacancySet1.contains("WorkHouse")),
                  () -> assertEquals(false, CompanyVacancySet1.contains("Deep Purple")));

        assertAll(() -> assertEquals(true, SalaryVacancySet1.contains(50)),
                  () -> assertEquals(true, SalaryVacancySet1.contains(30)),
                  () -> assertEquals(false, SalaryVacancySet1.contains(60)),
                  () -> assertEquals(false, SalaryVacancySet1.contains(40)));

        vacancyQuentin.setGetVacanciesMethod("GET_ONE_AT_LEAST_VACANCIES_BY_REQUIREMENTS");//Установка метода поиска

        GetVacanciesByVacancyDtoRequest getVacanciesByVacancyDtoRequest2 = //Создание запроса серверу
                new GetVacanciesByVacancyDtoRequest(registerAlexDtoResponse.getToken(), vacancyQuentin);
        String vacancy2 = gson.toJson(getVacanciesByVacancyDtoRequest2); //Перевод в json запроса

        String responseVacancy2 = server.getVacanciesByVacancy(vacancy2);//Отправка запроса серверу и получение от него ответа
        GetVacanciesByVacancyDtoResponse responseVacancies2 = //Ответ от сервера Set<Employee>
                gson.fromJson(responseVacancy2, GetVacanciesByVacancyDtoResponse.class); //Преобразование из json в response

        Set<String> NameVacancySet2 = new HashSet<>();
        Set<String> CompanyVacancySet2 = new HashSet<>();
        Set<Integer> SalaryVacancySet2 = new HashSet<>();
        for (VacancyDtoResponse vacancy : responseVacancies2.getVacancySet()) {
            NameVacancySet2.add(vacancy.getName());
            CompanyVacancySet2.add(vacancy.getCompany());
            SalaryVacancySet2.add(vacancy.getSalary());
        }

        //Проверяем содержание списка VacancySet
        assertAll(() -> assertEquals(true, NameVacancySet2.contains("Junior Developer")),
                  () -> assertEquals(true, NameVacancySet2.contains("Middle Developer")),
                  () -> assertEquals(false, NameVacancySet2.contains("Senior Developer")),
                  () -> assertEquals(false, NameVacancySet2.contains("Program Developer")));

        assertAll(() -> assertEquals(true, CompanyVacancySet2.contains("General electric")),
                  () -> assertEquals(true, CompanyVacancySet2.contains("Borman bros.")),
                  () -> assertEquals(false, CompanyVacancySet2.contains("WorkHouse")),
                  () -> assertEquals(false, CompanyVacancySet2.contains("Deep Purple")));

        assertAll(() -> assertEquals(true, SalaryVacancySet2.contains(50)),
                  () -> assertEquals(true, SalaryVacancySet2.contains(30)),
                  () -> assertEquals(false, SalaryVacancySet2.contains(60)),
                  () -> assertEquals(false, SalaryVacancySet2.contains(40)));

        vacancyQuentin.setGetVacanciesMethod("GET_ALL_VACANCIES_BY_REQUIREMENTS_AND_LEVEL");//Установка метода поиска

        GetVacanciesByVacancyDtoRequest getVacanciesByVacancyDtoRequest3 = //Создание запроса серверу
                new GetVacanciesByVacancyDtoRequest(registerAlexDtoResponse.getToken(), vacancyQuentin);
        String vacancy3 = gson.toJson(getVacanciesByVacancyDtoRequest3); //Перевод в json запроса

        String responseVacancy3 = server.getVacanciesByVacancy(vacancy3);//Отправка запроса серверу и получение от него ответа
        GetVacanciesByVacancyDtoResponse responseVacancies3 = //Ответ от сервера Set<Employee>
                gson.fromJson(responseVacancy3, GetVacanciesByVacancyDtoResponse.class); //Преобразование из json в response

        Set<String> NameVacancySet3 = new HashSet<>();
        Set<String> CompanyVacancySet3 = new HashSet<>();
        Set<Integer> SalaryVacancySet3 = new HashSet<>();
        for (VacancyDtoResponse vacancy : responseVacancies3.getVacancySet()) {
            NameVacancySet3.add(vacancy.getName());
            CompanyVacancySet3.add(vacancy.getCompany());
            SalaryVacancySet3.add(vacancy.getSalary());
        }

        //Проверяем содержание списка VacancySet
        assertAll(() -> assertEquals(true, NameVacancySet3.contains("Junior Developer")),
                  () -> assertEquals(true, NameVacancySet3.contains("Middle Developer")),
                  () -> assertEquals(false, NameVacancySet3.contains("Senior Developer")),
                  () -> assertEquals(true, NameVacancySet3.contains("Program Developer")));

        assertAll(() -> assertEquals(true, CompanyVacancySet3.contains("General electric")),
                  () -> assertEquals(true, CompanyVacancySet3.contains("Borman bros.")),
                  () -> assertEquals(false, CompanyVacancySet3.contains("WorkHouse")),
                  () -> assertEquals(true, CompanyVacancySet3.contains("Deep Purple")));

        assertAll(() -> assertEquals(true, SalaryVacancySet3.contains(50)),
                  () -> assertEquals(true, SalaryVacancySet3.contains(30)),
                  () -> assertEquals(false, SalaryVacancySet3.contains(60)),
                  () -> assertEquals(true, SalaryVacancySet3.contains(40)));

        //Проверка выхода и входа в сессию Employer
        UserLogoutDtoRequest userLogoutDtoRequestIan = new UserLogoutDtoRequest(registerIanDtoResponse.getToken());
        String logoutIan = gson.toJson(userLogoutDtoRequestIan);
        String logoutIanResponse = server.employerLogout(logoutIan);

        assertEquals("{}", logoutIanResponse); //Ian успешно вышел из сессии

        LoginUserDtoRequest loginUserDtoRequestIan = new LoginUserDtoRequest("deepGillan", "kyhgr46g");
        String ianToken = server.employerLogin(gson.toJson(loginUserDtoRequestIan));
        RegisterEmployerDtoResponse employeeIanResponse = gson.fromJson(ianToken, RegisterEmployerDtoResponse.class);

        assertEquals(gson.toJson(employeeIanResponse), ianToken); //Ian успешно вошел в сессию
    }
        @Test
        public void testErrorRegisterEmployee() {

        clearDataBase();

        RegisterEmployeeDtoRequest registerAlexDtoRequest = new RegisterEmployeeDtoRequest("Alex",
                "Merser", "", "alexm", "skq", "alexm@yandex.ru", new LinkedList());

        String requestAlex = gson.toJson(registerAlexDtoRequest);//Employee Alex
        String jsonAlexResponse = server.registerEmployee(requestAlex);//Registration Alex
        String wrongPasswordAlex = gson.toJson(new ErrorDtoResponse("WRONG_PASSWORD_LENGTH"));

        assertEquals(wrongPasswordAlex, jsonAlexResponse);

        RegisterEmployeeDtoRequest registerRobertDtoRequest = new RegisterEmployeeDtoRequest("",
                "Tyler", "", "bobtyler", "fdfee33fr", "bobtyler@mail.ru", new LinkedList());

        String requestBob = gson.toJson(registerRobertDtoRequest);
        String jsonBobResponse = server.registerEmployee(requestBob);
        String wrongFirstNameBob = gson.toJson(new ErrorDtoResponse("WRONG_FIRST_NAME"));

        assertEquals(wrongFirstNameBob, jsonBobResponse);

        RegisterEmployeeDtoRequest registerRickDtoRequest = new RegisterEmployeeDtoRequest("Rick",
                "Grimes", "", "rik", "fdd3rds3", "rickgrimes@yahoo.com", new LinkedList());
        String requestRick = gson.toJson(registerRickDtoRequest);
        String jsonRickResponse = server.registerEmployee(requestRick);
        String wrongLoginLengthRick = gson.toJson(new ErrorDtoResponse("WRONG_LOGIN_LENGTH"));

         assertEquals(wrongLoginLengthRick, jsonRickResponse);

         RegisterEmployeeDtoRequest registerTonyDtoRequest = new RegisterEmployeeDtoRequest("Anthony",
                "","","tonystark","4fdf33f","tonystark54@gmail.com", new LinkedList());
         String requestTony = gson.toJson(registerTonyDtoRequest);
         String jsonTonyResponse = server.registerEmployee(requestTony);
         String wrongLoginLengthTony = gson.toJson(new ErrorDtoResponse("WRONG_LAST_NAME"));

         assertEquals(wrongLoginLengthTony, jsonTonyResponse);
        }

    @Test
    public void testErrorLoginEmployee() {

        clearDataBase();

        LoginUserDtoRequest loginUserDtoRequestJack = new LoginUserDtoRequest("Jakie", "fdfewf3g");
        String JackToken = server.employerLogin(gson.toJson(loginUserDtoRequestJack));

        assertEquals("EMPLOYER_IS_NOT_FOUND", gson.fromJson(JackToken, ErrorDtoResponse.class).getError());
    }
}