package cana.codelessautomation.api.services.application.processors.mappers;

import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;

public interface ApplicationProcessorMapper {
    ApplicationDao mapApplicationDao(CreateApplicationDto createApplicationDto);

    ApplicationDao mapApplicationDao(DeleteApplicationDto deleteApplicationDto);

    ApplicationDao mapApplicationDao(UpdateApplicationDto updateApplicationDto);
}
