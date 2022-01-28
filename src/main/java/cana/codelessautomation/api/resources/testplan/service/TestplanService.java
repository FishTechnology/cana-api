package cana.codelessautomation.api.resources.testplan.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.testplan.service.dtos.*;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;

import java.util.List;

public interface TestplanService {
    List<ErrorMessageDto> createTestplan(CreateTestplanDto createTestplanDto) throws ValidationException;

    List<TestplanDao> getTestplans(Long applicationId) throws ValidationException;

    TestplanDao getTestplanById(Long applicationId, Long testplanId) throws ValidationException;

    List<ErrorMessageDto> deleteTestplan(DeleteTestplanDto testplanId) throws ValidationException;

    List<ErrorMessageDto> updateTestplan(UpdateTestplanDto updateTestplanModel) throws ValidationException;

    List<ErrorMessageDto> updateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) throws ValidationException;

    List<ErrorMessageDto> copyTestplan(CopyTestPlanDto copyTestPlanDto);
}
