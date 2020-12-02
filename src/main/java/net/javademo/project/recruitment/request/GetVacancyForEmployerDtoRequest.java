package net.javademo.project.recruitment.request;

public class GetVacancyForEmployerDtoRequest {
    String token;

    public GetVacancyForEmployerDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
