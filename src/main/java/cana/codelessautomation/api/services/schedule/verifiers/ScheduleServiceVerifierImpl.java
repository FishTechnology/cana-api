package cana.codelessautomation.api.services.schedule.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.environment.verifiers.EnvironmentVerifier;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.testplan.verifiers.TestplanVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ScheduleServiceVerifierImpl implements ScheduleServiceVerifier {

    @Inject
    TestplanVerifier testplanVerifier;

    @Inject
    EnvironmentVerifier environmentVerifier;

    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Override
    public List<ErrorMessageDto> verifyCreateSchedule(CreateScheduleDto createScheduleDto) {
        var errors = isTestPlanIdValid(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isUserId(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isEnvironmentIdValid(createScheduleDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        return isUserId(scheduleSummaryDto);
    }

    @Override
    public List<ErrorMessageDto> isUserId(ScheduleSummaryDto scheduleSummaryDto) {
        var response =customerServiceVerifier.isUserIdValid(scheduleSummaryDto.getUserId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        scheduleSummaryDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }


    @Override
    public List<ErrorMessageDto> isUserId(CreateScheduleDto createScheduleDto) {
        var response =customerServiceVerifier.isUserIdValid(createScheduleDto.getUserId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        createScheduleDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestPlanIdValid(CreateScheduleDto createScheduleDto) {
        var response = testplanVerifier.isTestplanIdValid(createScheduleDto.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        createScheduleDto.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentIdValid(CreateScheduleDto createScheduleDto) {
        var response = environmentVerifier.isEnvironmentIdValid(createScheduleDto.getEnvironmentId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        createScheduleDto.setEnvironment(response.getValue());
        return Collections.emptyList();
    }
}
