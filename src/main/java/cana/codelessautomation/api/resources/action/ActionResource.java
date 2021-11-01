package cana.codelessautomation.api.resources.action;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.action.mappers.ActionResourceMapper;
import cana.codelessautomation.api.resources.action.models.CreateActionModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.action.ActionService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class ActionResource {

    @Inject
    ActionResourceMapper actionResourceMapper;

    @Inject
    ActionService actionService;


    @POST
    @Path("testCases/{testCaseId}/actions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createAction(@Valid CreateActionModel createActionModel,
                                    @Valid @PathParam Long testCaseId) throws ValidationException {
        var createActionDto = actionResourceMapper.mapCreateActionDto(createActionModel, testCaseId);
        var errorMessages = actionService.createAction(createActionDto);
        return actionResourceMapper.mapResultModel(createActionDto, errorMessages);
    }
}
