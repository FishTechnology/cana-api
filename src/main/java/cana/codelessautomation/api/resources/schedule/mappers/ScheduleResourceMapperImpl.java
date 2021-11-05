package cana.codelessautomation.api.resources.schedule.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.CreateScheduleModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduleItemModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduleIterationModel;
import cana.codelessautomation.api.resources.schedule.models.SchedulePageModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ScheduleResourceMapperImpl implements ScheduleResourceMapper {
    @Override
    public ResultModel mapResultModel(CreateScheduleDto createScheduleDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createScheduleDto.getId());
        return resultModel;
    }

    @Override
    public CreateScheduleDto mapCreateScheduleDto(CreateScheduleModel createScheduleModel, Long testPlanId) {
        CreateScheduleDto createScheduleDto = new CreateScheduleDto();
        createScheduleDto.setEnvironmentId(createScheduleModel.getEnvironmentId());
        createScheduleDto.setUserId(createScheduleModel.getUserId());
        createScheduleDto.setTestPlanId(testPlanId);
        createScheduleDto.setIsCaptureNetworkTraffic(createScheduleModel.getIsCaptureNetworkTraffic());
        createScheduleDto.setIsDisableScreenshot(createScheduleModel.getIsDisableScreenshot());
        createScheduleDto.setIsRecordVideoEnabled(createScheduleModel.getIsRecordVideoEnabled());
        return createScheduleDto;
    }

    @Override
    public ScheduleSummaryDto mapScheduleSummaryDto(Long userId, int pageSize, int pageNumber) {
        ScheduleSummaryDto scheduleSummaryDto = new ScheduleSummaryDto();
        scheduleSummaryDto.setUserId(userId);
        scheduleSummaryDto.setPageSize(pageSize);
        scheduleSummaryDto.setPageNumber(pageNumber);
        return scheduleSummaryDto;
    }

    @Override
    public SchedulePageModel mapSchedulePageModel(ScheduleSummaryDto scheduleSummaryDto, List<ErrorMessageDto> errorMessages) {
        SchedulePageModel schedulePageModel = new SchedulePageModel();
        schedulePageModel.setPageSize(scheduleSummaryDto.getPageSize());
        schedulePageModel.setPageNumber(scheduleSummaryDto.getPageNumber());
        schedulePageModel.setTotalPage(scheduleSummaryDto.getTotalPageCount());
        List<ScheduleItemModel> scheduleItemModels = new ArrayList<>();
        for (ScheduleDao scheduleDao : scheduleSummaryDto.getScheduleDaos()) {
            ScheduleItemModel scheduleItemModel = new ScheduleItemModel();
            scheduleItemModel.setScheduleId(scheduleDao.getId());
            var scheduleIteration = scheduleDao.getScheduleIterations().get(0);
            scheduleItemModel.setStatus(scheduleIteration.getStatus().name());
            scheduleItemModel.setLastExecute(scheduleIteration.getModifiedOn().toString());
            scheduleItemModel.setEnvironmentName(scheduleDao.getEnvironmentDaos().getName());
            scheduleItemModel.setTestplanName(scheduleDao.getTestplanDaos().getName());
            scheduleItemModels.add(scheduleItemModel);
        }
        schedulePageModel.setScheduleItem(scheduleItemModels);
        return schedulePageModel;
    }

    @Override
    public List<ScheduleIterationModel> mapScheduleIterationModels(List<ScheduleIterationDao> scheduleIterationDaos) {
        List<ScheduleIterationModel> scheduleIterationModels = new ArrayList<>();
        for (ScheduleIterationDao scheduleIterationDao : scheduleIterationDaos) {
            ScheduleIterationModel scheduleIterationModel = new ScheduleIterationModel();
            scheduleIterationModel.setId(scheduleIterationDao.getId());
            scheduleIterationModel.setScheduleId(scheduleIterationDao.getScheduleId());
            scheduleIterationModel.setComments(scheduleIterationDao.getComments());
            scheduleIterationModel.setCreatedBy(scheduleIterationDao.getCreatedBy());
            scheduleIterationModel.setCreatedOn(scheduleIterationDao.getCreatedOn().toString());
            scheduleIterationModel.setModifiedBy(scheduleIterationDao.getModifiedBy());
            scheduleIterationModel.setModifiedOn(scheduleIterationDao.getModifiedOn().toString());
            scheduleIterationModel.setStatus(scheduleIterationDao.getStatus());
            scheduleIterationModel.setStartedOn(Objects.toString(scheduleIterationDao.getStartedOn()));
            scheduleIterationModel.setCompletedOn(Objects.toString(scheduleIterationDao.getCompletedOn()));
            scheduleIterationModel.setIsDisableScreenshot(scheduleIterationDao.getIsDisableScreenshot());
            scheduleIterationModel.setIsRecordVideoEnabled(scheduleIterationDao.getIsRecordVideoEnabled());
            scheduleIterationModel.setIsCaptureNetworkTraffic(scheduleIterationDao.getIsCaptureNetworkTraffic());
            scheduleIterationModels.add(scheduleIterationModel);
        }
        return scheduleIterationModels;
    }
}
