package cana.codelessautomation.api.resources.application.service.processors.mappers;

import cana.codelessautomation.api.resources.application.service.dtos.CreateApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;

public interface ApplicationProcessorMapper {
    ApplicationDao mapApplicationDao(CreateApplicationDto createApplicationDto);

    ApplicationDao mapApplicationDao(DeleteApplicationDto deleteApplicationDto);

    ApplicationDao mapApplicationDao(UpdateApplicationDto updateApplicationDto);
}
