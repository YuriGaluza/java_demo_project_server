package net.javademo.project.recruitment.request;

public class GetEmployeesByVacancyDtoRequest {

    String token;
    VacancyDtoRequest vacancy;

    public GetEmployeesByVacancyDtoRequest(String token, VacancyDtoRequest vacancy) {
        this.token = token;
        this.vacancy = vacancy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VacancyDtoRequest getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyDtoRequest vacancy) {
        this.vacancy = vacancy;
    }
}
