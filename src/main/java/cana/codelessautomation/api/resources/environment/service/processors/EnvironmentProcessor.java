package cana.codelessautomation.api.resources.environment.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;

import java.util.List;

public interface EnvironmentProcessor {
    List<ErrorMessageDto> processCreateEnvironment(CreateEnvironmentDto createEnvironment);

    List<ErrorMessageDto> deleteEnvironment(DeleteEnvironmentDto deleteEnvironment);

    List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironment);

    List<ErrorMessageDto> updateEnvironment(UpdateEnvironmentDto updateEnvironment);

    List<EnvironmentDao> processGetEnvironments(Long userId);

    List<EnvironmentDao> getEnvironments(Long userId);

    List<ErrorMessageDto> processDeleteEnvironment(DeleteEnvironmentDto deleteEnvironment);

    List<ErrorMessageDto> processUpdateEnvironment(UpdateEnvironmentDto updateEnvironment);
}
