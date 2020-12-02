package net.javademo.project.recruitment.response;

import java.util.Set;

public class GetVacanciesByVacancyDtoResponse {
    Set<VacancyDtoResponse> vacancySet;

    public GetVacanciesByVacancyDtoResponse(Set<VacancyDtoResponse> vacancySet) {
        this.vacancySet = vacancySet;
    }

    public Set<VacancyDtoResponse> getVacancySet() {
        return vacancySet;
    }

    public void setVacancySet(Set<VacancyDtoResponse> vacancySet) {
        this.vacancySet = vacancySet;
    }
}
