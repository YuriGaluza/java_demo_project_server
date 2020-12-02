package net.javademo.project.recruitment.response;

import net.javademo.project.recruitment.request.VacancyDtoRequest;

import java.util.Set;

public class GetVacancyForEmployerDtoResponse {
    Set<VacancyDtoRequest> vacancySet;

    public GetVacancyForEmployerDtoResponse(Set<VacancyDtoRequest> vacancySet) {
        this.vacancySet = vacancySet;
    }

    public Set<VacancyDtoRequest> getVacancySet() {
        return vacancySet;
    }

    public void setVacancySet(Set<VacancyDtoRequest> vacancySet) {
        this.vacancySet = vacancySet;
    }
}

