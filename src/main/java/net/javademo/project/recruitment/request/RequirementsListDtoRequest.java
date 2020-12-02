package net.javademo.project.recruitment.request;

import java.util.LinkedList;
import java.util.List;

public class RequirementsListDtoRequest {

    private List<RequirementDtoRequest> requirementDtoRequestList = new LinkedList<>();

    public RequirementsListDtoRequest(List<RequirementDtoRequest> requirementDtoRequestList) {
        this.requirementDtoRequestList = requirementDtoRequestList;
    }

    public List<RequirementDtoRequest> getRequirementDtoRequestList() {
        return requirementDtoRequestList;
    }

    public void setRequirementDtoRequestList(List<RequirementDtoRequest> requirementDtoRequestList) {
        this.requirementDtoRequestList = requirementDtoRequestList;
    }
}
