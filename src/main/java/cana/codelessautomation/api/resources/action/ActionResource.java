package cana.codelessautomation.api.resources.action;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.action.mappers.ActionResourceMapper;
import cana.codelessautomation.api.resources.action.models.ActionDetailModel;
import cana.codelessautomation.api.resources.action.models.CreateActionModel;
import cana.codelessautomation.api.resources.action.models.UpdateActionOrderModel;
import cana.codelessautomation.api.resources.action.service.ActionService;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
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
public class ActionResource {

    @Inject
    ActionResourceMapper actionResourceMapper;

    @Inject
    ActionService actionService;


    @POST
    @Path("testCases/{testCaseId}/actions/uiControl")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createAction(@Valid CreateActionModel createActionModel,
                                    @Valid @PathParam Long testCaseId) throws ValidationException {
        var createActionDto = actionResourceMapper.mapCreateActionDto(createActionModel, testCaseId);
        var errorMessages = actionService.createAction(createActionDto);
        return actionResourceMapper.mapResultModel(createActionDto, errorMessages);
    }

    @GET
    @Path("testCases/{testCaseId}/actions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ActionDetailModel> getActionsByTestCaseId(@Valid @PathParam Long testCaseId) throws ValidationException {
        var getActionsByTestCaseIdDto = actionResourceMapper.mapGetActionsByTestCaseIdDto(testCaseId);
        var errorMessages = actionService.getActionsByTestCaseId(getActionsByTestCaseIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        if (CollectionUtils.isEmpty(getActionsByTestCaseIdDto.getActionDaos())) {
            return Collections.emptyList();
        }
        return actionResourceMapper.mapActionDetailModels(getActionsByTestCaseIdDto);
    }

    @DELETE
    @Path("testCases/{testCaseId}/actions/{actionId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteActionById(@Valid @PathParam Long testCaseId, @Valid @PathParam Long actionId) throws ValidationException {
        var deleteActionByIdDto = actionResourceMapper.mapDeleteActionByIdDto(testCaseId, actionId);
        var errorMessages = actionService.deleteActionById(deleteActionByIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return Collections.emptyList();
    }

    @PUT
    @Path("testCases/{testCaseId}/actions/order")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateActionOrder(@Valid UpdateActionOrderModel updateActionOrderModel,
                                                     @Valid @PathParam Long testCaseId) throws ValidationException {
        var updateActionOrderDto = actionResourceMapper.mapUpdateActionOrderDto(updateActionOrderModel, testCaseId);
        var errorMessages = actionService.updateActionOrder(updateActionOrderDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return Collections.emptyList();
    }
}
