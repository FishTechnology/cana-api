package cana.codelessautomation.api.services.testplan;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testplan.dtos.CreateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;

import java.util.List;

public interface TestplanService {
    List<ErrorMessageDto> createTestplan(CreateTestplanDto createTestplanDto) throws ValidationException;

    List<TestplanDao> getTestplans(Long userId) throws ValidationException;

    TestplanDao getTestplanById(Long testplanId) throws ValidationException;

    List<ErrorMessageDto> deleteTestplan(DeleteTestplanDto testplanId) throws ValidationException;

    List<ErrorMessageDto> updateTestplan(UpdateTestplanDto updateTestplanModel) throws ValidationException;

    List<ErrorMessageDto> updateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) throws ValidationException;
}
