package cana.codelessautomation.api.services.application;

import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationService {
    List<ErrorMessageDto> createApplication(CreateApplicationDto createApplicationDto);

    List<ApplicationDao> getApplications(String userId);

    List<ErrorMessageDto> deleteApplication(DeleteApplicationDto applicationId);

    List<ErrorMessageDto> updateApplication(UpdateApplicationDto updateApplicationDto);

    ApplicationDao getApplicationById(Long applicationId);
}
