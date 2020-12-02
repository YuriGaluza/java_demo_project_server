package net.javademo.project.recruitment.server;

import com.google.gson.Gson;
import net.javademo.project.recruitment.database.DataBase;
import net.javademo.project.recruitment.service.EmployeeService;
import net.javademo.project.recruitment.service.EmployerService;

import java.io.*;

public class Server {


    private EmployerService employerService = new EmployerService();
    private EmployeeService employeeService = new EmployeeService();
    private DataBase dataBase = DataBase.getInstance();
    private Gson gson = new Gson();

    public static void main(String[] args) {
        Server server = new Server();
    }

    public void startServer(String savedDataFileName) {
        if (savedDataFileName.equals(null) || savedDataFileName.equals("")) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(savedDataFileName)))) {
                dataBase = (DataBase) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer(String savedDataFileName) throws IOException {
        if (savedDataFileName.equals(null) || savedDataFileName.equals("")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(savedDataFileName)))) {
                oos.writeObject(dataBase);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataBase =  null;
        }
    }

    public String registerEmployer(String requestJsonString) {
        return employerService.registerEmployer(requestJsonString);
    }

    public String registerEmployee(String requestJsonString) {
        return employeeService.registerEmployee(requestJsonString);
    }

    public String addVacancy(String requestJsonString) throws ServerException {
        return employerService.addVacancy(requestJsonString);
    }

    public String getAllVacanciesForEmployee(String requestJsonString) {
        return employeeService.getAllVacanciesForEmployee(requestJsonString);
    }

    public String getAllVacanciesForEmployer(String requestJsonString) {
        return employerService.getAllVacanciesForEmployer(requestJsonString);
    }

    public String insertSkill(String requestJsonString) throws ServerException {
        return employeeService.insertSkill(requestJsonString);
    }

    public String employeeLogin(String requestJsonString) {
        return employeeService.userLogin(requestJsonString);
    }

    public String employerLogin(String requestJsonString) {
        return employerService.employerLogin(requestJsonString);
    }

    public String employeeLogout(String requestJsonString) {
        return employeeService.employeeLogout(requestJsonString);
    }

    public String employerLogout(String requestJsonString) {
        return employerService.userLogout(requestJsonString);
    }

    public String getEmployeesByVacancy(String requestJsonString) {
        return employerService.getEmployeesByVacancy(requestJsonString);
    }

    public String getVacanciesByVacancy(String requestJsonString) {
        return employeeService.getVacanciesByVacancy(requestJsonString);
    }

    public String getVacancyForEmployer(String requestJsonString) {
    return employerService.getVacancyForEmployer(requestJsonString);
    }

    public String addSkill(String requestJsonString) {
        return employeeService.addSkill(requestJsonString);
    }

    public void clearDataBase() {
        dataBase.clearDataBase();
    }

}
