package cana.codelessautomation.api.resources.testplan.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.CreateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;

import java.util.List;

public interface TestplanService {
    List<ErrorMessageDto> createTestplan(CreateTestplanDto createTestplanDto) throws ValidationException;

    List<TestplanDao> getTestplans(Long userId) throws ValidationException;

    TestplanDao getTestplanById(Long testplanId) throws ValidationException;

    List<ErrorMessageDto> deleteTestplan(DeleteTestplanDto testplanId) throws ValidationException;

    List<ErrorMessageDto> updateTestplan(UpdateTestplanDto updateTestplanModel) throws ValidationException;

    List<ErrorMessageDto> updateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) throws ValidationException;
}
