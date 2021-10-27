package cana.codelessautomation.api.services.testcase;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testcase.dtos.*;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;

import java.util.List;

public interface TestCaseService {
    List<ErrorMessageDto> createTestCase(CreateTestCaseDto createTestCaseDto) throws ValidationException;

    List<ErrorMessageDto> createTestCaseByPlanId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) throws ValidationException;

    List<TestCaseDao> getTestCaseByUserId(Long userId) throws ValidationException;

    List<ErrorMessageDto> getTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto testPlanId) throws ValidationException;

    List<ErrorMessageDto> checkTestCaseIsDeletable(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto) throws ValidationException;

    void getTestCaseById(GetTestCaseByIdDto getTestCaseByIdDto) throws ValidationException;

    List<ErrorMessageDto> updateTestCaseById(UpdateTestCaseByIdDto updateTestCaseByIdDto) throws ValidationException;

    List<ErrorMessageDto> updateTestCaseByTestPlanId(UpdateTestCaseByTestPlanIdDto updateTestCaseByTestPlanIdDto);
}
