package net.javademo.project.recruitment.request;

public class SkillDtoRequest {

    private String name;
    private Integer level;
    private static final int MAX_LEVEL_VALUE = 5;
    private static final int MIN_LEVEL_VALUE = 1;

    public SkillDtoRequest(String name, Integer level) {
        this.name = name;
        this.level = level;
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
}
