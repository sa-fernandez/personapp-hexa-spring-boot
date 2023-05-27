package co.edu.javeriana.as.personapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;

@Mapper
public class TelefonoMapperRest {

    @Autowired
    private PersonaMapperRest personaMapperRest;

    public TelefonoResponse fromDomainToAdapterRestMaria(Phone phone) {
        return fromDomainToAdapterRest(phone, "MariaDB");
    }

    public TelefonoResponse fromDomainToAdapterRestMongo(Phone phone) {
        return fromDomainToAdapterRest(phone, "MongoDB");
    }

    public TelefonoResponse fromDomainToAdapterRest(Phone phone, String database) {
        return new TelefonoResponse(
                phone.getNumber(),
                phone.getCompany(),
                personaMapperRest.fromDomainToAdapterRest(phone.getOwner(), database),
                database,
                "OK");
    }

    public Phone fromAdapterToDomain(TelefonoRequest request) {
        return new Phone(request.getNumber(), request.getCompany(),
                personaMapperRest.fromAdapterToDomain(request.getOwner()));
    }

}
