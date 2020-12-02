package net.javademo.project.recruitment.response;

import net.javademo.project.recruitment.request.VacancyDtoRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GetAllVacanciesDtoResponse {

    List<Set<VacancyDtoRequest>> allVacancies = new LinkedList<>();
}
