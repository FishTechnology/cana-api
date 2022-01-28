package cana.codelessautomation.api.resources.testcase;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.testcase.mappers.TestCaseResourceMapper;
import cana.codelessautomation.api.resources.testcase.models.*;
import cana.codelessautomation.api.resources.testcase.service.TestCaseService;
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
    TestCaseResourceMapper testCaseResourceMapper;

    @Inject
    TestCaseService testCaseService;

    @POST
    @Path("/applications/{applicationId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createTestCase(@Valid CreateTestCaseModel createTestCaseModel) throws ValidationException {
        var createTestCaseDto = testCaseResourceMapper.mapCreateTestCaseDto(createTestCaseModel);
        var errorMessages = testCaseService.createTestCase(createTestCaseDto);
        return testCaseResourceMapper.mapResultModel(createTestCaseDto, errorMessages);
    }

    @POST
    @Path("/applications/{applicationId}/testPlans/{testPlanId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createTestCaseByPlanId(@Valid @PathParam Long testPlanId, @Valid CreateTestCaseByTestPlanIdModel createTestCaseByTestPlanIdModel) throws ValidationException {
        var createTestCaseByTestPlanId = testCaseResourceMapper.mapCreateTestCaseByTestPlanIdDto(createTestCaseByTestPlanIdModel, testPlanId);
        var errorMessages = testCaseService.createTestCaseByPlanId(createTestCaseByTestPlanId);
        return testCaseResourceMapper.mapResultModel(createTestCaseByTestPlanId, errorMessages);
    }

    @GET
    @Path("/applications/{applicationId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TestCaseModel> getTestCaseByUserId(@Valid @QueryParam Long userId) throws ValidationException {
        var testCaseDaos = testCaseService.getTestCaseByUserId(userId);
        if (CollectionUtils.isEmpty(testCaseDaos)) {
            return Collections.emptyList();
        }
        return testCaseResourceMapper.mapTestCaseModels(testCaseDaos);
    }

    @GET
    @Path("/applications/{applicationId}/testPlans/{testPlanId}/testCases")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TestCaseModel> getTestCaseByTestPlanId(@Valid @PathParam Long applicationId, @Valid @PathParam Long testPlanId) throws ValidationException {
        var getTestCaseByTestPlanIdDto = testCaseResourceMapper.mapGetTestCaseByTestPlanIdDto(applicationId, testPlanId);
        var errors = testCaseService.getTestCaseByTestPlanId(getTestCaseByTestPlanIdDto);
        if (CollectionUtils.isEmpty(getTestCaseByTestPlanIdDto.getTestCaseDaos())) {
            return Collections.emptyList();
        }
        return testCaseResourceMapper.mapTestCaseModels(getTestCaseByTestPlanIdDto);
    }

    @GET
    @Path("/applications/{applicationId}/testCases/{testCaseId}/isDeletable")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CheckTestCaseIsDeletableModel checkTestCaseIsDeletable(@Valid @PathParam Long testCaseId) throws ValidationException {
        var checkTestCaseIsDeletableDto = testCaseResourceMapper.mapCheckTestCaseIsDeletableDto(testCaseId);
        var errors = testCaseService.checkTestCaseIsDeletable(checkTestCaseIsDeletableDto);
        return testCaseResourceMapper.mapCheckTestCaseIsDeletableModel(checkTestCaseIsDeletableDto, errors);
    }

    @GET
    @Path("/applications/{applicationId}/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TestCaseModel getTestCaseById(@Valid @PathParam Long testCaseId) throws ValidationException {
        var getTestCaseByIdDto = testCaseResourceMapper.mapGetTestCaseByIdDto(testCaseId);
        testCaseService.getTestCaseById(getTestCaseByIdDto);
        return testCaseResourceMapper.mapTestCaseModel(getTestCaseByIdDto);
    }

    @PUT
    @Path("/applications/{applicationId}/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
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
    @Path("/applications/{applicationId}/testPlans/{testPlanId}/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateTestCaseByTestPlanId(@Valid @PathParam Long testPlanId, @Valid @PathParam Long testCaseId,
                                                              @Valid UpdateTestCaseModel updateTestCaseModel) throws ValidationException {
        var updateTestCaseByTestPlanIdDto = testCaseResourceMapper.mapUpdateTestCaseByTestPlanIdDto(testPlanId, testCaseId, updateTestCaseModel);
        var errors = testCaseService.updateTestCaseByTestPlanId(updateTestCaseByTestPlanIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/applications/{applicationId}/testPlans/{testPlanId}/testCases/order")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateTestCaseOrder(@Valid @PathParam Long testPlanId,
                                                       @Valid UpdateTestCaseOrderModel updateTestCaseOrderModel) throws ValidationException {
        var updateTestCaseOrderDto = testCaseResourceMapper.mapUpdateTestCaseOrderDto(testPlanId, updateTestCaseOrderModel);
        var errors = testCaseService.updateTestCaseOrder(updateTestCaseOrderDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/applications/{applicationId}/testPlans/{testPlanId}/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteTestCase(@Valid @PathParam Long applicationId,
                                                  @Valid @PathParam Long testPlanId,
                                                  @Valid @PathParam Long testCaseId) throws ValidationException {
        var deleteTestCaseDto = testCaseResourceMapper.mapDeleteTestCaseDto(applicationId, testPlanId, testCaseId);
        var errors = testCaseService.deleteTestCase(deleteTestCaseDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("/applications/{applicationId}/testPlans/{testPlanId}/testCases/{testCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteTestCaseByTestCaseId(@Valid @PathParam Long applicationId,
                                                              @Valid @PathParam Long testPlanId,
                                                              @Valid @PathParam Long testCaseId) throws ValidationException {
        var deleteTestCaseDto = testCaseResourceMapper.mapDeleteTestCaseDto(applicationId, testCaseId);
        var errors = testCaseService.deleteTestCase(deleteTestCaseDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return CanaUtility.getErrorMessageModels(errors);
        }
        return Collections.emptyList();
    }
}
