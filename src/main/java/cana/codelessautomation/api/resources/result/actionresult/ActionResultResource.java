package cana.codelessautomation.api.resources.result.actionresult;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.result.actionresult.mappers.ActionResultMapper;
import cana.codelessautomation.api.resources.result.actionresult.models.UpdateActionResultModel;
import cana.codelessautomation.api.services.results.action.ActionResultService;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/api")
public class ActionResultResource {

    @Inject
    ActionResultService actionResultService;

    @Inject
    ActionResultMapper actionResultMapper;

    @POST
    @Path("testcaseResults/{testCaseResultId}/actionsResult/{actionResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateActionResult(
            @Valid Long testCaseResultId,
            @Valid Long actionResultId,
            @Valid UpdateActionResultModel updateActionResultModel
    ) throws ValidationException {
        var updateActionResultDto = actionResultMapper.mapUpdateActionResultDto(testCaseResultId, actionResultId, updateActionResultModel);
        var errorMessages = actionResultService.updateActionResult(updateActionResultDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }
}
