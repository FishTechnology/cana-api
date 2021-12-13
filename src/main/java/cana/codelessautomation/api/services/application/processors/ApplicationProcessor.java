package cana.codelessautomation.api.services.application.processors;

import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationProcessor {
    List<ErrorMessageDto> processCreateApplication(CreateApplicationDto createApplicationDto);

    List<ErrorMessageDto> createApplication(CreateApplicationDto createApplicationDto);

    List<ApplicationDao> processGetApplications(String userId);

    List<ErrorMessageDto> processDeleteApplication(DeleteApplicationDto applicationId);

    List<ErrorMessageDto> deleteApplication(DeleteApplicationDto deleteApplicationDto);

    List<ErrorMessageDto> processUpdateApplication(UpdateApplicationDto updateApplicationDto);

    List<ErrorMessageDto> updateApplication(UpdateApplicationDto updateApplicationDto);

    ApplicationDao processGetApplicationById(Long applicationId);
}
