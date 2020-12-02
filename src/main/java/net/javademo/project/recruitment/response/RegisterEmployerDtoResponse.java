package net.javademo.project.recruitment.response;

public class RegisterEmployerDtoResponse {
    public String token;

    public RegisterEmployerDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
