package cana.codelessautomation.api.resources.schedule;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.mappers.ReScheduleModel;
import cana.codelessautomation.api.resources.schedule.mappers.ScheduleResourceMapper;
import cana.codelessautomation.api.resources.schedule.models.*;
import cana.codelessautomation.api.resources.schedule.service.ScheduleService;
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
    @Path("/schedules/{scheduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ScheduleIterationModel> getScheduleIterations(@Valid @PathParam Long scheduleId) throws ValidationException {
        var scheduleIterationDtos = scheduleService.getScheduleIterations(scheduleId);
        if (CollectionUtils.isEmpty(scheduleIterationDtos)) {
            return Collections.emptyList();
        }
        return scheduleResourceMapper.mapScheduleIterationModels(scheduleIterationDtos);
    }

    @GET
    @Path("/schedules/{scheduleId}/scheduleIterations/{scheduleIterationId}/result")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ScheduleIterationResultModel getScheduleIterationResult(@Valid @PathParam Long scheduleId, @Valid @PathParam Long scheduleIterationId) throws ValidationException {
        var scheduleIterationResultDto = scheduleResourceMapper.mapScheduleIterationResultDto(scheduleId, scheduleIterationId);
        var errors = scheduleService.getScheduleIterationResult(scheduleIterationResultDto);
        return scheduleResourceMapper.mapScheduleIterationResultModel(errors, scheduleIterationResultDto);
    }

    @GET
    @Path("/applications/{applicationId}/schedules/runningSchedule")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ScheduleModel getRunningScheduleByAppId(@Valid @PathParam Long applicationId) throws ValidationException {
        var scheduleEntity = scheduleService.getRunningScheduleByAppId(applicationId);
        return scheduleResourceMapper.mapScheduleIterationResultModel(scheduleEntity);
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
        var errors = scheduleService.updateScheduleStatus(updateScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/schedules/{scheduleId}/reschedule")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> reSchedule(@Valid @PathParam Long scheduleId, @Valid ReScheduleModel reScheduleModel) throws ValidationException {
        var reScheduleStatusDto = scheduleResourceMapper.mapReScheduleStatusDto(scheduleId, reScheduleModel);
        var errors = scheduleService.reSchedule(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }
}
