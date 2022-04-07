package cana.codelessautomation.api.resources.schedule;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.mappers.ReScheduleModel;
import cana.codelessautomation.api.resources.schedule.mappers.ScheduleResourceMapper;
import cana.codelessautomation.api.resources.schedule.models.*;
import cana.codelessautomation.api.resources.schedule.service.ScheduleService;
import cana.codelessautomation.api.resources.schedule.service.dtos.UpdateScheduleStatusReadyDto;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Path("/api")
public class ScheduleResource {
    @Inject
    ScheduleResourceMapper scheduleResourceMapper;

    @Inject
    ScheduleService scheduleService;

    @POST
    @Path("/applications/{applicationId}/testPlans/{testPlanId}/schedules")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createSchedule(@Valid @PathParam Long applicationId, @Valid @PathParam Long testPlanId, @Valid CreateScheduleModel createScheduleModel) throws ValidationException {
        var createScheduleDto = scheduleResourceMapper.mapCreateScheduleDto(applicationId, createScheduleModel, testPlanId);
        var errorMessages = scheduleService.createSchedule(createScheduleDto);
        return scheduleResourceMapper.mapResultModel(createScheduleDto, errorMessages);
    }

    @GET
    @Path("/applications/{applicationId}/schedules")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SchedulePageModel getScheduleSummary(@Valid @PathParam Long applicationId, @Valid @DefaultValue("10") @QueryParam int pageSize, @Valid @DefaultValue("0") @QueryParam int pageNumber) throws ValidationException {
        var scheduleSummaryDto = scheduleResourceMapper.mapScheduleSummaryDto(applicationId, pageSize, pageNumber);
        var errorMessages = scheduleService.getScheduleSummary(scheduleSummaryDto);
        return scheduleResourceMapper.mapSchedulePageModel(scheduleSummaryDto, errorMessages);
    }

    @GET
    @Path("/schedules/{scheduleId}/recurse")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ScheduleDetailModel getScheduler(@Valid @PathParam Long scheduleId) throws ValidationException {
        var scheduleDetailEntity = scheduleService.getScheduler(scheduleId);
        if (scheduleDetailEntity == null) {
            return null;
        }
        return scheduleResourceMapper.mapScheduleDetailModel(scheduleDetailEntity);
    }

    @GET
    @Path("/applications/{applicationId}/schedules/{scheduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ScheduleIterationModel> getScheduleIterations(@Valid @PathParam Long applicationId, @Valid @PathParam Long scheduleId) throws ValidationException {
        var getScheduleIterationsDto = scheduleResourceMapper.mapGetScheduleIterationsDto(applicationId, scheduleId);
        var errors = scheduleService.getScheduleIterations(getScheduleIterationsDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleResourceMapper.mapScheduleIterationModels(getScheduleIterationsDto);
    }

    @GET
    @Path("/applications/{applicationId}/schedules/{scheduleId}/scheduleIterations/{scheduleIterationId}/result")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ScheduleIterationResultModel getScheduleIterationResult(@Valid @PathParam Long applicationId, @Valid @PathParam Long scheduleId, @Valid @PathParam Long scheduleIterationId) throws ValidationException {
        var scheduleIterationResultDto = scheduleResourceMapper.mapScheduleIterationResultDto(scheduleId, scheduleIterationId);
        var errors = scheduleService.getScheduleIterationResult(scheduleIterationResultDto);
        return scheduleResourceMapper.mapScheduleIterationResultModel(errors, scheduleIterationResultDto);
    }

//    @GET
//    @Path("/schedules/{scheduleId}/scheduleIterations/{scheduleIterationId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public ScheduleIterationResultModel getScheduleIterations(@Valid @PathParam Long scheduleId, @Valid @PathParam Long scheduleIterationId) throws ValidationException {
//        var scheduleIterationsDto = scheduleResourceMapper.mapScheduleIterationsDto(scheduleId, scheduleIterationId);
//        var errors = scheduleService.getScheduleIterations(scheduleIterationsDto);
//        return scheduleResourceMapper.mapScheduleIterationResultModel(errors, scheduleIterationsDto);
//    }

    @GET
    @Path("/applications/{applicationId}/schedules/runningSchedule")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ScheduleModel> getRunningScheduleByAppId(@Valid @PathParam Long applicationId) throws ValidationException {
        var scheduleEntities = scheduleService.getRunningScheduleByAppId(applicationId);
        return scheduleResourceMapper.mapScheduleModels(scheduleEntities);
    }

    @GET
    @Path("/schedules/runningSchedule")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ScheduleModel getRunningSchedule() throws ValidationException {
        var scheduleEntity = scheduleService.getRunningSchedule();
        if (Objects.isNull(scheduleEntity)) {
            return null;
        }
        return scheduleResourceMapper.mapScheduleIterationResultModel(scheduleEntity);
    }

    @GET
    @Path("/schedules/scheduleToExecute")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ScheduleModel getScheduleToExecute() throws ValidationException {
        var scheduleEntity = scheduleService.getScheduleToExecute();
        if (Objects.isNull(scheduleEntity)) {
            return null;
        }
        return scheduleResourceMapper.mapScheduleIterationResultModel(scheduleEntity);
    }

    @POST
    @Path("/schedules/{scheduleId}/copy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> copyTestPlanDetail(@Valid @PathParam Long scheduleId) throws ValidationException {
        var copyTestPlanDetailDto = scheduleResourceMapper.mapCopyTestPlanDetailDto(scheduleId);
        var errors = scheduleService.copyTestPlanDetail(copyTestPlanDetailDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/schedules/{scheduleId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateScheduleStatus(@Valid @PathParam Long scheduleId, @Valid UpdateScheduleStatusModel updateScheduleStatusModel) throws ValidationException {
        var updateScheduleStatusDto = scheduleResourceMapper.mapUpdateScheduleStatusDto(scheduleId, updateScheduleStatusModel);
        return updateScheduleStatus(updateScheduleStatusDto);
    }

    @PUT
    @Path("/applications/{applicationId}/schedules/{scheduleId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateScheduleStatusByAppId(@Valid @PathParam Long applicationId, @Valid @PathParam Long scheduleId, @Valid UpdateScheduleStatusModel updateScheduleStatusModel) throws ValidationException {
        var updateScheduleStatusDto = scheduleResourceMapper.mapUpdateScheduleStatusDto(applicationId, scheduleId, updateScheduleStatusModel);
        return updateScheduleStatus(updateScheduleStatusDto);
    }

    @PUT
    @Path("/applications/{applicationId}/schedules/{scheduleId}/reschedule")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> reSchedule(@Valid @PathParam Long applicationId, @Valid @PathParam Long scheduleId, @Valid ReScheduleModel reScheduleModel) throws ValidationException {
        var reScheduleStatusDto = scheduleResourceMapper.mapReScheduleStatusDto(applicationId, scheduleId, reScheduleModel);
        var errors = scheduleService.reSchedule(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("schedules/{scheduleId}/iterations/{iterationId}/session")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateScheduleSession(@Valid @PathParam Long scheduleId,
                                                         @Valid @PathParam Long iterationId,
                                                         @Valid UpdateScheduleSessionModel updateScheduleSession) throws ValidationException {
        var updateScheduleSessionDto = scheduleResourceMapper.mapUpdateScheduleSessionDto(scheduleId, iterationId, updateScheduleSession);
        var errors = scheduleService.updateScheduleSession(updateScheduleSessionDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    private List<ErrorMessageModel> updateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var errors = scheduleService.updateScheduleStatus(updateScheduleStatusReadyDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }
}
