package cana.codelessautomation.api.services.envvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;

import java.util.List;

public interface EnvVariableService {
    EnvPageSetDetailDto getEnvVariables(long userId, int pageNumber, int pageSize) throws ValidationException;

    List<ErrorMessageDto> deleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto) throws ValidationException;

    List<ErrorMessageDto> createEnvVariable(CreateEnvVariableDto createEnvVariableDto) throws ValidationException;
}
