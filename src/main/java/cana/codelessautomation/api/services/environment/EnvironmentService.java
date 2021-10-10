package cana.codelessautomation.api.services.environment;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;

import java.util.List;

public interface EnvironmentService {
    List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironmentDto) throws ValidationException;
}
