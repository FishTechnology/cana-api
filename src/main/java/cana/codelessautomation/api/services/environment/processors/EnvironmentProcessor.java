package cana.codelessautomation.api.services.environment.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;

import java.util.List;

public interface EnvironmentProcessor {
    List<ErrorMessageDto> processCreateEnvironment(CreateEnvironmentDto createEnvironment);

    List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironment);
}
