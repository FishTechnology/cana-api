package cana.codelessautomation.api.services.environment;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;

import java.util.List;

public interface EnvironmentService {
    List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironmentDto) throws ValidationException;

    List<EnvironmentDao> getEnvironments(Long userId) throws ValidationException;

    List<ErrorMessageDto> deleteEnvironment(DeleteEnvironmentDto environmentId) throws ValidationException;
}
