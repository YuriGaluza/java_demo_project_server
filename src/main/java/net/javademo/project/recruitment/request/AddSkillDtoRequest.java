package net.javademo.project.recruitment.request;

public class AddSkillDtoRequest {

    private String name;
    private Integer level;
    private static final int MAX_LEVEL_VALUE = 5;
    private static final int MIN_LEVEL_VALUE = 1;
    private String token;

    public AddSkillDtoRequest(String name, Integer level, String token) {
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

    public static int getMaxLevelValue() {
        return MAX_LEVEL_VALUE;
    }

    public static int getMinLevelValue() {
        return MIN_LEVEL_VALUE;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
