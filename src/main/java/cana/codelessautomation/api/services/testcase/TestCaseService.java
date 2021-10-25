package cana.codelessautomation.api.services.testcase;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.dtos.GetTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;

import java.util.List;

public interface TestCaseService {
    List<ErrorMessageDto> createTestCase(CreateTestCaseDto createTestCaseDto) throws ValidationException;

    List<ErrorMessageDto> createTestCaseByPlanId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) throws ValidationException;

    List<TestCaseDao> getTestCaseByUserId(Long userId) throws ValidationException;

    List<ErrorMessageDto> getTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto testPlanId) throws ValidationException;
}
