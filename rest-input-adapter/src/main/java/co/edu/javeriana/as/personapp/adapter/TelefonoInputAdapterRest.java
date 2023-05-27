package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mapper.TelefonoMapperRest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class TelefonoInputAdapterRest {

    @Autowired
    @Qualifier("phoneOutputAdapterMaria")
    private PhoneOutputPort phoneOutputPortMaria;

    @Autowired
    @Qualifier("phoneOutputAdapterMongo")
    private PhoneOutputPort phoneOutputPortMongo;
    
    @Autowired
    private TelefonoMapperRest telefonoMapperRest;

    PhoneInputPort phoneInputPort;

    private String setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			phoneInputPort = new PhoneUseCase(phoneOutputPortMaria);
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			phoneInputPort = new PhoneUseCase(phoneOutputPortMongo);
			return  DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

    public List<TelefonoResponse> historial(String database) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return phoneInputPort.findAll().stream().map(telefonoMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return phoneInputPort.findAll().stream().map(telefonoMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
			
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<TelefonoResponse>();
		}
	}

	public TelefonoResponse crearTelefono(TelefonoRequest request) {
		try {
			String database = setPersonOutputPortInjection(request.getDatabase());
			Phone phone = phoneInputPort.create(telefonoMapperRest.fromAdapterToDomain(request));
			if(database.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return telefonoMapperRest.fromDomainToAdapterRestMaria(phone);
			}else {
				return telefonoMapperRest.fromDomainToAdapterRestMongo(phone);
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new TelefonoResponse("", "", null, "", "");
		}
	}

	public TelefonoResponse obtenerTelefono(String database, String number) {
		log.info("Into obtenerTelefono PersonaEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return telefonoMapperRest.fromDomainToAdapterRestMaria(phoneInputPort.findOne(number));
			}else {
				return telefonoMapperRest.fromDomainToAdapterRestMongo(phoneInputPort.findOne(number));
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new TelefonoResponse("", "", null, "", "");
		} catch (NoExistException e) {
			log.warn(e.getMessage());
			return new TelefonoResponse("", "", null, "", "");
		}
	}
	
	public TelefonoResponse editarTelefono(TelefonoRequest request) {
		log.info("Into editarTelefono TelefonoEntity in Input Adapter");
		try {
			String database = setPersonOutputPortInjection(request.getDatabase());
			Phone phone = phoneInputPort.edit(request.getNumber(), telefonoMapperRest.fromAdapterToDomain(request));
			if(database.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return telefonoMapperRest.fromDomainToAdapterRestMaria(phone);
			}else {
				return telefonoMapperRest.fromDomainToAdapterRestMongo(phone);
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new TelefonoResponse("", "", null, "", "");
		} catch (NumberFormatException e) {
			log.warn(e.getMessage());
			return new TelefonoResponse("", "", null, "", "");
		} catch (NoExistException e) {
			log.warn(e.getMessage());
			return new TelefonoResponse("", "", null, "", "");
		}
	}

	public Boolean eliminarTelefono(String database, String number) {
		log.info("Into eliminarTelefono TelefonoEntity in Input Adapter");
		try {
			setPersonOutputPortInjection(database);
			return phoneInputPort.drop(number);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return false;
		} catch (NoExistException e) {
			log.warn(e.getMessage());
			return false;
		}
	}

}
