package cana.codelessautomation.api.resources.testplan.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.testplan.service.dtos.CreateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;

import java.util.List;

public interface TestplanVerifier {
    List<ErrorMessageDto> verifyCreateTestplan(CreateTestplanDto createTestplan);

    List<ErrorMessageDto> isTestplanNameValid(CreateTestplanDto createTestplan);

    List<ErrorMessageDto> isUserIdValid(CreateTestplanDto createTestplan);

    List<ErrorMessageDto> verifyGetTestplans(Long userId);

    List<ErrorMessageDto> isUserIdValid(Long userId);

    List<ErrorMessageDto>  verifyDeleteTestplan(DeleteTestplanDto testplanId);

    List<ErrorMessageDto> isTestplanIdValid(DeleteTestplanDto deleteTestplan);

    KeyValue<List<ErrorMessageDto>, TestplanDao> isTestplanIdValid(Long testplanId);

    List<ErrorMessageDto> verifyUpdateTestplan(UpdateTestplanDto updateTestplanModel);

    List<ErrorMessageDto> isTestplanNameValid(UpdateTestplanDto updateTestplan);

    List<ErrorMessageDto> isTestplanIdValid(UpdateTestplanDto updateTestplanModel);

    List<ErrorMessageDto> verifyUpdateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus);

    List<ErrorMessageDto> isTestplanStatusValid(UpdateTestplanStatusDto updateTestplanStatus);

    List<ErrorMessageDto> isUserIdValid(UpdateTestplanStatusDto updateTestplanStatus);

    List<ErrorMessageDto> isTestplanIdValid(UpdateTestplanStatusDto updateTestplanStatus);
}
