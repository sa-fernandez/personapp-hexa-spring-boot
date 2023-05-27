package co.edu.javeriana.as.personapp.model.response;

import co.edu.javeriana.as.personapp.model.request.EstudiosRequest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.request.ProfesionRequest;

public class EstudiosResponse extends EstudiosRequest {

    private String status;

    public EstudiosResponse(PersonaRequest person, ProfesionRequest profession, String graduationDate,
            String universityName, String database, String status) {
        super(person, profession, graduationDate, universityName, database);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
