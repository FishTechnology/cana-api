package cana.codelessautomation.api.resources.application.service;

import cana.codelessautomation.api.resources.application.service.dtos.CreateApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationService {
    List<ErrorMessageDto> createApplication(CreateApplicationDto createApplicationDto);

    List<ApplicationDao> getApplications(String userId);

    List<ErrorMessageDto> deleteApplication(DeleteApplicationDto applicationId);

    List<ErrorMessageDto> updateApplication(UpdateApplicationDto updateApplicationDto);

    ApplicationDao getApplicationById(Long applicationId);
}
