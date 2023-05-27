package co.edu.javeriana.as.personapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudiosRequest {
    private PersonaRequest person;
    private ProfesionRequest profession;
    private String graduationDate;
    private String universityName;
    private String database;
}
