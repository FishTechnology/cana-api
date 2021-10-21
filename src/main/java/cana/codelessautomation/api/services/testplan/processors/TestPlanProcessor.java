package cana.codelessautomation.api.services.testplan.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testplan.dtos.CreateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;

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
}
