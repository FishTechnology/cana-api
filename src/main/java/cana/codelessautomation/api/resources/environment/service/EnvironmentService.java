package cana.codelessautomation.api.resources.environment.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;

import java.util.List;

public interface EnvironmentService {
    List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironmentDto) throws ValidationException;

    List<EnvironmentDao> getEnvironments(Long userId) throws ValidationException;

    List<ErrorMessageDto> deleteEnvironment(DeleteEnvironmentDto environmentId) throws ValidationException;

    EnvironmentDao getEnvironment(Long environmentId);

    List<ErrorMessageDto> updateEnvironment(UpdateEnvironmentDto updateEnvironmentDto) throws ValidationException;
}
