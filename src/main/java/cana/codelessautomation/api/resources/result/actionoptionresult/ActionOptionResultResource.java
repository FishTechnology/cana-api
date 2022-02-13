package cana.codelessautomation.api.resources.result.actionoptionresult;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.result.actionoptionresult.mapper.ActionOptionResultResourceMapper;
import cana.codelessautomation.api.resources.result.actionoptionresult.models.ActionOptionResultModel;
import cana.codelessautomation.api.resources.result.actionoptionresult.models.UpdateActionOptionResultModel;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.ActionOptionResultService;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/api")
public class ActionOptionResultResource {
    @Inject
    ActionOptionResultService actionOptionResultService;

    @Inject
    ActionOptionResultResourceMapper actionOptionResultResourceMapper;

    @GET
    @Path("/actionResults/{actionResultId}/actionOptionResults")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ActionOptionResultModel> getActionOptionResultsByActionResultId(@Valid @PathParam Long actionResultId) throws ValidationException {
        var actionOptionResultDtos = actionOptionResultService.getActionOptionResultsByActionResultId(actionResultId);
        if (CollectionUtils.isEmpty(actionOptionResultDtos)) {
            return null;
        }
        return actionOptionResultResourceMapper.mapActionOptionResultModels(actionOptionResultDtos);
    }

    @POST
    @Path("/actionResults/{actionResultId}/actionOptionResults/{actionOptionResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateActionOptionResult(
            @Valid @PathParam Long actionResultId,
            @Valid @PathParam Long actionOptionResultId,
            @Valid UpdateActionOptionResultModel updateActionOptionResultModel
    ) throws ValidationException {
        var updateActionOptionResultDto = actionOptionResultResourceMapper.mapUpdateActionOptionResultDto(actionResultId, actionOptionResultId, updateActionOptionResultModel);
        var errorMessages = actionOptionResultService.updateActionOptionResult(updateActionOptionResultDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }
}
