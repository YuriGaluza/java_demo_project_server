package net.javademo.project.recruitment;

import com.google.gson.Gson;
import net.javademo.project.recruitment.dao.EmployerDao;
import net.javademo.project.recruitment.daoimpl.EmployeeDaoImpl;
import net.javademo.project.recruitment.daoimpl.EmployerDaoImpl;
import net.javademo.project.recruitment.model.Employee;
import net.javademo.project.recruitment.model.Employer;
import net.javademo.project.recruitment.model.Skill;
import net.javademo.project.recruitment.request.LoginUserDtoRequest;
import net.javademo.project.recruitment.request.RegisterEmployerDtoRequest;
import net.javademo.project.recruitment.server.ServerErrorCode;
import net.javademo.project.recruitment.server.ServerException;
import net.javademo.project.recruitment.service.EmployerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

public class TestMockEmployeeDaoImpl {

    private Gson gson = new Gson();

    @Test
    void testEmployeeDaoGetEmployeeByToken() {

        EmployeeDaoImpl employeeDao = Mockito.mock(EmployeeDaoImpl.class);
        String token = UUID.randomUUID().toString();

        Employee employee = new Employee("Alex", "Mercer", "",
                "alexm", "skwjw2q", "alexm@yandex.ru", new LinkedList());

        Mockito.when(employeeDao.getEmployeeByToken(token)).thenReturn(employee);

        Assert.assertEquals(employee, employeeDao.getEmployeeByToken(token));
    }

    @Test
    void testEmployeeDaoGetEmployeeBySkill() throws ServerException {

        EmployerDaoImpl employerDao = Mockito.mock(EmployerDaoImpl.class);

        Employee employeeBob = new Employee("Robert",
                "Tyler", "", "bobtyler", "fdfee33fr", "bobtyler@mail.ru", new LinkedList());
        Skill skillBob = new Skill("C", 2);
        employeeBob.addSkill(skillBob);

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employeeBob);

        Mockito.when(employerDao.getEmployeesBySkill(skillBob)).thenReturn(employeeSet);

        Assert.assertEquals(employeeSet, employerDao.getEmployeesBySkill(skillBob));
    }

    @Test
    public void testLogIn() {

        EmployerDao mockEmployerDao = Mockito.mock(EmployerDao.class);
        Employer employer = new Employer("", "", "", "", "", "", "", "");
        Mockito.when(mockEmployerDao.getEmployerByToken(Mockito.any(String.class))).thenReturn(employer);

        EmployerService employerService = new EmployerService();
        RegisterEmployerDtoRequest registerIanDtoRequest = new RegisterEmployerDtoRequest("Ian", "Gillan",
                "", "deepGillan", "kyhgr46g", "iangillan75@yandex.ru", "Deep Purple", "London, Hounslow");
        String ianToken = employerService.registerEmployer(gson.toJson(registerIanDtoRequest));

        assertTrue(ianToken.contains("token"));
    }

    @Test
    public void testLogInTokensDifferent() {

        EmployerDao mockEmployerDao = Mockito.mock(EmployerDao.class);
        Employer userEmployer = new Employer("", "", "", "", "", "", "", "");
        Mockito.when(mockEmployerDao.getEmployerByToken(Mockito.any(String.class))).thenReturn(userEmployer);

        EmployerService userService = new EmployerService();
        RegisterEmployerDtoRequest registerIanDtoRequest = new RegisterEmployerDtoRequest("Ian", "Gillan",
                "", "deepGillan", "kyhgr46g", "iangillan75@yandex.ru", "Deep Purple", "London, Hounslow");

        String requestIan = gson.toJson(registerIanDtoRequest);
        userService.registerEmployer(requestIan);
        LoginUserDtoRequest loginUserDtoRequestIan = new LoginUserDtoRequest("deepGillan", "kyhgr46g");
        userService.employerLogin(gson.toJson(loginUserDtoRequestIan));
        String jsonresponse1 = userService.employerLogin(gson.toJson(loginUserDtoRequestIan));
        String jsonresponse2 = userService.employerLogin(gson.toJson(loginUserDtoRequestIan));

        assertNotEquals(jsonresponse1, jsonresponse2);
    }

    @Test
    public void leaveFromServerTest() throws Exception {
        EmployerDao mockUserDao = Mockito.mock(EmployerDao.class);
        EmployerService employerServiceService = new EmployerService();
        doNothing()
                .doThrow(new ServerException(ServerErrorCode.EMPLOYER_IS_NOT_FOUND))
                .when(mockUserDao).userLogout("c980779a-2992-429d-9b6c-ffbd8c313987");
        String response = employerServiceService.userLogout("{\"token\": \"c980779a-2992-429d-9b6c-ffbd8c313987\"}");

        assertEquals("{\"error\":\"net.javademo.project.recruitment.server.ServerException: ALREADY_LOGOUT\"}", response);
    }
}
