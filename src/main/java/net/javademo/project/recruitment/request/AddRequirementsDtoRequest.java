package net.javademo.project.recruitment.request;

public class AddRequirementsDtoRequest {

    private String name;
    private Integer level;
    private static final int MAX_LEVEL_VALUE = 5;
    private static final int MIN_LEVEL_VALUE = 1;
    private boolean mandatory;
    private VacancyDtoRequest vacancy;
    private String token;

    public AddRequirementsDtoRequest(String name, Integer level, boolean mandatory, VacancyDtoRequest vacancy, String token) {
        this.name = name;
        this.level = level;
        this.mandatory = mandatory;
        this.vacancy = vacancy;
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

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public VacancyDtoRequest getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyDtoRequest vacancy) {
        this.vacancy = vacancy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
