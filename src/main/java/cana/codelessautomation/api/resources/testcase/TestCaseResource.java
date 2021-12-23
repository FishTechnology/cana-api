package cana.codelessautomation.api.resources.testcase;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.testcase.mappers.TestCaseServiceMapper;
import cana.codelessautomation.api.resources.testcase.models.*;
import cana.codelessautomation.api.resources.testcase.service.TestCaseService;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
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
public class TestCaseResource {
    @Inject
    TestCaseServiceMapper testCaseResourceMapper;

    @Inject
    TestCaseService testCaseService;

    @POST
    @Path("/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createTestCase(@Valid CreateTestCaseModel createTestCaseModel) throws ValidationException {
        var createTestCaseDto = testCaseResourceMapper.mapCreateTestCaseDto(createTestCaseModel);
        var errorMessages = testCaseService.createTestCase(createTestCaseDto);
        return testCaseResourceMapper.mapResultModel(createTestCaseDto, errorMessages);
    }

    @POST
    @Path("/testPlans/{testPlanId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createTestCaseByPlanId(@Valid @PathParam Long testPlanId, @Valid CreateTestCaseByTestPlanIdModel createTestCaseByTestPlanIdModel) throws ValidationException {
        var createTestCaseByTestPlanId = testCaseResourceMapper.mapCreateTestCaseByTestPlanIdDto(createTestCaseByTestPlanIdModel, testPlanId);
        var errorMessages = testCaseService.createTestCaseByPlanId(createTestCaseByTestPlanId);
        return testCaseResourceMapper.mapResultModel(createTestCaseByTestPlanId, errorMessages);
    }

    @GET
    @Path("/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TestCaseModel> getTestCaseByUserId(@Valid @QueryParam Long userId) throws ValidationException {
        var testCaseDaos = testCaseService.getTestCaseByUserId(userId);
        return testCaseResourceMapper.mapTestCaseModels(testCaseDaos);
    }

    @GET
    @Path("/testPlans/{testPlanId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TestCaseModel> getTestCaseByTestPlanId(@Valid @PathParam Long testPlanId) throws ValidationException {
        var getTestCaseByTestPlanIdDto = testCaseResourceMapper.mapGetTestCaseByTestPlanIdDto(testPlanId);
        var errors = testCaseService.getTestCaseByTestPlanId(getTestCaseByTestPlanIdDto);
        return testCaseResourceMapper.mapTestCaseModels(getTestCaseByTestPlanIdDto);
    }

    @GET
    @Path("/testCases/{testCaseId}/isDeletable")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CheckTestCaseIsDeletableModel checkTestCaseIsDeletable(@Valid @PathParam Long testCaseId) throws ValidationException {
        var checkTestCaseIsDeletableDto = testCaseResourceMapper.mapCheckTestCaseIsDeletableDto(testCaseId);
        var errors = testCaseService.checkTestCaseIsDeletable(checkTestCaseIsDeletableDto);
        return testCaseResourceMapper.mapCheckTestCaseIsDeletableModel(checkTestCaseIsDeletableDto, errors);
    }

    @GET
    @Path("/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TestCaseModel getTestCaseById(@Valid @PathParam Long testCaseId) throws ValidationException {
        var getTestCaseByIdDto = testCaseResourceMapper.mapGetTestCaseByIdDto(testCaseId);
        testCaseService.getTestCaseById(getTestCaseByIdDto);
        return testCaseResourceMapper.mapTestCaseModel(getTestCaseByIdDto);
    }

    @PUT
    @Path("/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ErrorMessageModel> updateTestCaseById(@Valid @PathParam Long testCaseId,
                                                      @Valid UpdateTestCaseModel updateTestCaseModel) throws ValidationException {
        var updateTestCaseByIdDto = testCaseResourceMapper.mapUpdateTestCaseByIdDto(testCaseId, updateTestCaseModel);
        var errors = testCaseService.updateTestCaseById(updateTestCaseByIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/testPlans/{testPlanId}/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ErrorMessageModel> updateTestCaseByTestPlanId(@Valid @PathParam Long testPlanId, @Valid @PathParam Long testCaseId,
                                                              @Valid UpdateTestCaseModel updateTestCaseModel) throws ValidationException {
        var updateTestCaseByTestPlanIdDto = testCaseResourceMapper.mapUpdateTestCaseByTestPlanIdDto(testPlanId, testCaseId, updateTestCaseModel);
        var errors = testCaseService.updateTestCaseByTestPlanId(updateTestCaseByTestPlanIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }
}
