package net.javademo.project.recruitment.request;

import java.util.List;

public class GetEmployeesMethodDtoRequest {

    List<SkillDtoRequest> skillList;
    String token;

    public List<SkillDtoRequest> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillDtoRequest> skillList) {
        this.skillList = skillList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
