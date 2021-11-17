package cana.codelessautomation.api.resources.result.testplanresult;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.result.testplanresult.mappers.TestPlanResultMapper;
import cana.codelessautomation.api.resources.result.testplanresult.models.TestPlanResultModel;
import cana.codelessautomation.api.resources.result.testplanresult.models.UpdateTestPlanResultAsCompletedModel;
import cana.codelessautomation.api.services.results.testplan.TestPlanResultService;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Path("/api")
public class TestPlanResultResource {

    @Inject
    TestPlanResultService testPlanResultService;

    @Inject
    TestPlanResultMapper testPlanResultMapper;

    @PUT
    @Path("scheduleIterationIds/{scheduleIterationId}/testPlanResults/{testPlanResultId}/markAsStarted")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel updateTestPlanResultAsStarted(@Valid @PathParam Long scheduleIterationId, @Valid @PathParam Long testPlanResultId) throws ValidationException {
        return new ResultModel();
    }

    @PUT
    @Path("scheduleIterationIds/{scheduleIterationId}/testPlanResults/{testPlanResultId}/markAsStarted")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel updateTestPlanResultAsCompleted(@Valid @PathParam Long scheduleIterationId, @Valid @PathParam Long testPlanResultId, UpdateTestPlanResultAsCompletedModel
            updateTestPlanResultAsCompletedModel) throws ValidationException {
        return new ResultModel();
    }

    @PUT
    @Path("scheduleIterationIds/{scheduleIterationId}/testPlanResults/{testPlanResultId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateTestPlanResultStatus(@Valid @PathParam Long scheduleIterationId,
                                                              @Valid @PathParam Long testPlanResultId,
                                                              @Valid UpdateTestPlanResultAsCompletedModel updateTestPlanResultAsCompletedModel) throws ValidationException {
        var updateTestPlanResultStatusDto = testPlanResultMapper.mapUpdateTestPlanResultStatusDto(scheduleIterationId, testPlanResultId, updateTestPlanResultAsCompletedModel);
        var errorMessages = testPlanResultService.updateTestPlanResultStatus(updateTestPlanResultStatusDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }

    @GET
    @Path("scheduleIterationIds/{scheduleIterationId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TestPlanResultModel getTestPlanResultBySchIterId(@Valid @PathParam Long scheduleIterationId) throws ValidationException {
        var testPlanResultDao = testPlanResultService.getTestPlanResultBySchIterId(scheduleIterationId);
        if (Objects.isNull(testPlanResultDao)) {
            return null;
        }
        return testPlanResultMapper.mapTestPlanResultModel(testPlanResultDao);
    }
}
