package cana.codelessautomation.api.resources.testplan;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.testplan.mappers.TestplanResourceMapper;
import cana.codelessautomation.api.resources.testplan.models.*;
import cana.codelessautomation.api.resources.testplan.service.TestplanService;
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

@Path("/api")
public class TestplanResource {

    @Inject
    TestplanResourceMapper testplanResourceMapper;

    @Inject
    TestplanService testplanService;

    @POST
    @Path("/applications/{applicationId}/testPlans")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createTestplan(@Valid @PathParam Long applicationId, @Valid CreateTestplanModel createTestplanModel) throws ValidationException {
        var createTestplanDto = testplanResourceMapper.mapCreateTestplanDto(applicationId, createTestplanModel);
        var errorMessages = testplanService.createTestplan(createTestplanDto);
        return testplanResourceMapper.mapResultModel(createTestplanDto, errorMessages);
    }

    @GET
    @Path("/applications/{applicationId}/testplans")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TestPlanModel> getTestplans(@Valid @PathParam Long applicationId, @Valid @QueryParam Long userId) throws ValidationException {
        var testPlanDaos = testplanService.getTestplans(applicationId, userId);
        return testplanResourceMapper.mapTestPlanModels(testPlanDaos);
    }

    @GET
    @Path("/applications/{applicationId}/testPlans/{testplanId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TestPlanModel getTestplanById(@Valid @PathParam Long applicationId, @Valid @PathParam Long testplanId) throws ValidationException {
        var testPlanDao = testplanService.getTestplanById(applicationId, testplanId);
        return testplanResourceMapper.mapTestPlanModel(testPlanDao);
    }

    @DELETE
    @Path("/applications/{applicationId}/testPlans/{testplanId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteTestplan(@Valid @PathParam Long testplanId) throws ValidationException {
        var deleteTestplanDto = testplanResourceMapper.mapDeleteTestplanDto(testplanId);
        var errorMessages = testplanService.deleteTestplan(deleteTestplanDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return CanaUtility.getErrorMessageModels(errorMessages);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/applications/{applicationId}/testPlans/{testplanId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateTestplan(@Valid @PathParam Long testplanId, @Valid UpdateTestplanModel updateTestplanModel) throws ValidationException {
        var updateTestplan = testplanResourceMapper.mapUpdateTestplanDto(updateTestplanModel, testplanId);
        var errorMessages = testplanService.updateTestplan(updateTestplan);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return CanaUtility.getErrorMessageModels(errorMessages);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/applications/{applicationId}/testPlans/{testplanId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateTestplanStatus(@Valid @PathParam Long testplanId, @Valid UpdateTestplanStatusModel updateTestplanStatusModel) throws ValidationException {
        var updateTestplanStatus = testplanResourceMapper.mapUpdateTestplanStatusDto(updateTestplanStatusModel, testplanId);
        var errorMessages = testplanService.updateTestplanStatus(updateTestplanStatus);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return CanaUtility.getErrorMessageModels(errorMessages);
        }
        return Collections.emptyList();
    }

    @POST
    @Path("/applications/{applicationId}/testPlans/{testplanId}/copy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel copyTestplan(@Valid @PathParam Long testplanId, @Valid CopyTestPlanModel copyTestPlanModel) throws ValidationException {
        var copyTestPlanDto = testplanResourceMapper.mapCopyTestPlanDto(copyTestPlanModel, testplanId);
        var errorMessages = testplanService.copyTestplan(copyTestPlanDto);
        return testplanResourceMapper.mapResultModel(errorMessages, copyTestPlanDto);
    }
}
