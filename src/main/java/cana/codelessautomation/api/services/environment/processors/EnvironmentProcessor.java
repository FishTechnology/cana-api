package cana.codelessautomation.api.services.environment.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;

import java.util.List;

public interface EnvironmentProcessor {
    List<ErrorMessageDto> processCreateEnvironment(CreateEnvironmentDto createEnvironment);

    List<ErrorMessageDto> deleteEnvironment(DeleteEnvironmentDto deleteEnvironment);

    List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironment);

    List<EnvironmentDao> processGetEnvironments(Long userId);

    List<EnvironmentDao> getEnvironments(Long userId);

    List<ErrorMessageDto> processDeleteEnvironment(DeleteEnvironmentDto deleteEnvironment);
}
