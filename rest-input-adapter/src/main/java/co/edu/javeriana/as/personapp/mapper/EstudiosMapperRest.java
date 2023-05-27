package co.edu.javeriana.as.personapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.EstudiosRequest;
import co.edu.javeriana.as.personapp.model.response.EstudiosResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EstudiosMapperRest {

    @Autowired
    private PersonaMapperRest personaMapperRest;

    @Autowired
    private ProfesionMapperRest profesionMapperRest;

    public EstudiosResponse fromDomainToAdapterRestMaria(Study study) {
        return fromDomainToAdapterRest(study, "MariaDB");
    }

    public EstudiosResponse fromDomainToAdapterRestMongo(Study study) {
        return fromDomainToAdapterRest(study, "MongoDB");
    }

    public EstudiosResponse fromDomainToAdapterRest(Study study, String database) {
        return new EstudiosResponse(
                personaMapperRest.fromDomainToAdapterRest(study.getPerson(), database),
                profesionMapperRest.fromDomainToAdapterRest(study.getProfession(), database),
                study.getGraduationDate() + "",
                study.getUniversityName(),
                database,
                "OK");
    }

    public Study fromAdapterToDomain(EstudiosRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        return new Study(personaMapperRest.fromAdapterToDomain(request.getPerson()),
                profesionMapperRest.fromAdapterToDomain(request.getProfession()),
                LocalDate.parse(request.getGraduationDate(), formatter), request.getUniversityName());
    }

}
