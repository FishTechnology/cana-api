package cana.codelessautomation.api.resources.application.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.application.service.dtos.CreateApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;

import java.util.List;

public interface ApplicationProcessor {
    List<ErrorMessageDto> processCreateApplication(CreateApplicationDto createApplicationDto);

    List<ErrorMessageDto> createApplicationConfig(CreateApplicationDto createApplicationDto);

    List<ErrorMessageDto> createApplication(CreateApplicationDto createApplicationDto);

    List<ApplicationDao> processGetApplications(String userId);

    List<ErrorMessageDto> processDeleteApplication(DeleteApplicationDto applicationId);

    List<ErrorMessageDto> deleteApplication(DeleteApplicationDto deleteApplicationDto);

    List<ErrorMessageDto> processUpdateApplication(UpdateApplicationDto updateApplicationDto);

    List<ErrorMessageDto> updateApplication(UpdateApplicationDto updateApplicationDto);

    ApplicationDao processGetApplicationById(Long applicationId);
}
