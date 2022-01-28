package cana.codelessautomation.api.resources.testplan.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.*;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;

import java.util.List;

public interface TestPlanProcessor {
    List<ErrorMessageDto> processorCreateTestplan(CreateTestplanDto createTestplan);

    List<ErrorMessageDto> createTestplan(CreateTestplanDto createTestplan);

    List<TestplanDao> processorGetTestplans(Long userId);

    List<TestplanDao> getTestplans(Long userId);

    List<ErrorMessageDto> processorDeleteTestplan(DeleteTestplanDto testplanId);

    List<ErrorMessageDto> deleteTestplan(DeleteTestplanDto deleteTestplan);

    List<ErrorMessageDto> processorUpdateTestplan(UpdateTestplanDto updateTestplanModel);

    List<ErrorMessageDto> updateTestplan(UpdateTestplanDto updateTestplanModel);

    List<ErrorMessageDto> processorUpdateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus);

    List<ErrorMessageDto> updateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus);

    List<ErrorMessageDto> processorCopyTestplan(CopyTestPlanDto copyTestPlanDto);

    List<ErrorMessageDto> copyTestCases(CopyTestPlanDto copyTestPlanDto);

    List<ErrorMessageDto> createTestplan(CopyTestPlanDto copyTestPlanDto);
}
