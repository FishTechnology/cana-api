package cana.codelessautomation.api.services.application.verifiers;

import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;

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
