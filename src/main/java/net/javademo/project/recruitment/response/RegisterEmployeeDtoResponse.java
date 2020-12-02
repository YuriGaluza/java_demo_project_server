package net.javademo.project.recruitment.response;

public class RegisterEmployeeDtoResponse {

    public String token;

    public RegisterEmployeeDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
