package cana.codelessautomation.api.resources.result.testcaseresult;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.result.testcaseresult.mappers.TestCaseResultMapper;
import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.models.UpdateTestCaseResultAsCompletedModel;
import cana.codelessautomation.api.services.results.testcase.TestCaseResultService;
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
public class TestCaseResource {

    @Inject
    TestCaseResultService testCaseResultService;

    @Inject
    TestCaseResultMapper testCaseResultMapper;

    @PUT
    @Path("testPlanResults/{testPlanResultId}/testCaseResults/{testCaseResultId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateTestCaseResultStatus(@Valid @PathParam Long testPlanResultId,
                                                              @Valid @PathParam Long testCaseResultId,
                                                              @Valid UpdateTestCaseResultAsCompletedModel updateTestCaseResultAsCompletedModel) throws ValidationException {
        var updateTestCaseResultStatusDto = testCaseResultMapper.mapUpdateTestCaseResultStatusDto(testPlanResultId, testCaseResultId, updateTestCaseResultAsCompletedModel);
        var errorMessages = testCaseResultService.updateTestCaseResultStatus(updateTestCaseResultStatusDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }

    @GET
    @Path("testPlanResults/{testPlanResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TestCaseResultModel> getTestCaseResultByPlanResultId(@Valid @PathParam Long testPlanResultId) throws ValidationException {
        var testPlanResultDao = testCaseResultService.getTestCaseResultByPlanResultId(testPlanResultId);
        if (Objects.isNull(testPlanResultDao)) {
            return null;
        }
        return testCaseResultMapper.mapTestCaseResultModel(testPlanResultDao);
    }
}
