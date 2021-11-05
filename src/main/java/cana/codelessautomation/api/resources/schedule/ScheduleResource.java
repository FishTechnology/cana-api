package cana.codelessautomation.api.resources.schedule;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.mappers.ScheduleResourceMapper;
import cana.codelessautomation.api.resources.schedule.models.CreateScheduleModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduleIterationModel;
import cana.codelessautomation.api.resources.schedule.models.SchedulePageModel;
import cana.codelessautomation.api.services.schedule.ScheduleService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class ScheduleResource {
    @Inject
    ScheduleResourceMapper scheduleResourceMapper;

    @Inject
    ScheduleService scheduleService;

    @POST
    @Path("/testPlans/{testPlanId}/schedules")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createSchedule(
            @Valid @PathParam Long testPlanId,
            @Valid CreateScheduleModel createScheduleModel) throws ValidationException {
        var createScheduleDto = scheduleResourceMapper.mapCreateScheduleDto(createScheduleModel,testPlanId);
        var errorMessages = scheduleService.createSchedule(createScheduleDto);
        return scheduleResourceMapper.mapResultModel(createScheduleDto, errorMessages);
    }

    @GET
    @Path("/schedules")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SchedulePageModel getScheduleSummary(@Valid @QueryParam Long userId,
                                               @Valid @DefaultValue("10") @QueryParam int pageSize,
                                               @Valid @DefaultValue("0") @QueryParam int pageNumber) throws ValidationException {
        var scheduleSummaryDto = scheduleResourceMapper.mapScheduleSummaryDto(userId,pageSize,pageNumber);
        var errorMessages = scheduleService.getScheduleSummary(scheduleSummaryDto);
        return scheduleResourceMapper.mapSchedulePageModel(scheduleSummaryDto, errorMessages);
    }

    @GET
    @Path("/schedules/{scheduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ScheduleIterationModel> getScheduleIterations(@Valid @PathParam Long scheduleId) throws ValidationException {
        var scheduleIterationDtos = scheduleService.getScheduleIterations(scheduleId);
        return scheduleResourceMapper.mapScheduleIterationModels(scheduleIterationDtos);
    }
}
