package cana.codelessautomation.api.resources.result.actionresult;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.result.actionresult.mappers.ActionResultResourceMapper;
import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultModel;
import cana.codelessautomation.api.resources.result.actionresult.models.UpdateActionResultModel;
import cana.codelessautomation.api.resources.result.actionresult.service.ActionResultService;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
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
public class ActionResultResource {

    @Inject
    ActionResultService actionResultService;

    @Inject
    ActionResultResourceMapper actionResultResourceMapper;

    @POST
    @Path("testcaseResults/{testCaseResultId}/actionsResult/{actionResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateActionResult(
            @Valid @PathParam Long testCaseResultId,
            @Valid @PathParam Long actionResultId,
            @Valid UpdateActionResultModel updateActionResultModel
    ) throws ValidationException {
        var updateActionResultDto = actionResultResourceMapper.mapUpdateActionResultDto(testCaseResultId, actionResultId, updateActionResultModel);
        var errorMessages = actionResultService.updateActionResult(updateActionResultDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }

    @GET
    @Path("testcaseResults/{testCaseResultId}/actionsResult")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ActionResultModel> getActionResultsByTestCaseResultId(@Valid @PathParam Long testCaseResultId) throws ValidationException {
        var actionResultDtos = actionResultService.getActionResultsByTestCaseResultId(testCaseResultId);
        if (Objects.isNull(actionResultDtos)) {
            return null;
        }
        return actionResultResourceMapper.mapActionResultModels(actionResultDtos);
    }
}
