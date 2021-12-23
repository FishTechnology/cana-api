package cana.codelessautomation.api.resources.application.service.verifiers;

import cana.codelessautomation.api.resources.application.service.dtos.CreateApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;

import java.util.List;

public interface ApplicationVerifier {
    List<ErrorMessageDto> verifyCreateApplication(CreateApplicationDto createApplicationDto);

    List<ErrorMessageDto> isNameValid(CreateApplicationDto createApplicationDto);

    List<ErrorMessageDto> isUserIdValid(CreateApplicationDto createApplicationDto);

    List<ErrorMessageDto> verifyDeleteApplication(DeleteApplicationDto deleteApplicationDto);

    List<ErrorMessageDto> isApplicationIdValid(DeleteApplicationDto deleteApplicationDto);

    List<ErrorMessageDto> verifyUpdateApplication(UpdateApplicationDto updateApplicationDto);

    List<ErrorMessageDto> isNameValid(UpdateApplicationDto updateApplicationDto);

    List<ErrorMessageDto> isApplicationIdValid(UpdateApplicationDto updateApplicationDto);

    KeyValue<List<ErrorMessageDto>, ApplicationDao> isApplicationIdValid(Long applicationId);

    List<ErrorMessageDto> isUserIdValid(UpdateApplicationDto updateApplicationDto);
}
