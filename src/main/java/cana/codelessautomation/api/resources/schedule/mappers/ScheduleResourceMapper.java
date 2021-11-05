package cana.codelessautomation.api.resources.schedule.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.CreateScheduleModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduleIterationModel;
import cana.codelessautomation.api.resources.schedule.models.SchedulePageModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;

import java.util.List;

public interface ScheduleResourceMapper {
    ResultModel mapResultModel(CreateScheduleDto createScheduleDto, List<ErrorMessageDto> errorMessages);

    CreateScheduleDto mapCreateScheduleDto(CreateScheduleModel createScheduleModel, Long testPlanId);

    ScheduleSummaryDto mapScheduleSummaryDto(Long userId, int pageSize, int pageNumber);

    SchedulePageModel mapSchedulePageModel(ScheduleSummaryDto scheduleSummaryDto, List<ErrorMessageDto> errorMessages);

    List<ScheduleIterationModel> mapScheduleIterationModels(List<ScheduleIterationDao> scheduleIterationDaos);
}
