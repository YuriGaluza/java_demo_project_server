package net.javademo.project.recruitment.request;

public class InsertSkillDtoRequest {

    private String name;
    private Integer level;
    private String token;

    public InsertSkillDtoRequest(String name, Integer level, String token) {
        this.name = name;
        this.level = level;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
