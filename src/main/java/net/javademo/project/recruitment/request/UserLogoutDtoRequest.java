package net.javademo.project.recruitment.request;

public class UserLogoutDtoRequest {

    String token;

    public UserLogoutDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
