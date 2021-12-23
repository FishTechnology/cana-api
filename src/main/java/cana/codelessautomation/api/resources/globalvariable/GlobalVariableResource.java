package cana.codelessautomation.api.resources.globalvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.globalvariable.mappers.GlobalVariableResourceMapper;
import cana.codelessautomation.api.resources.globalvariable.models.CreateGlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.GlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.UpdateGlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.service.GlobalVariableService;
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
public class GlobalVariableResource {
    @Inject
    GlobalVariableResourceMapper globalVariableResourceMapper;

    @Inject
    GlobalVariableService globalVariableService;

    @GET
    @Path("/globalVariables")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<GlobalVariableModel> getGlobalVariables(@Valid @QueryParam Long userId) throws ValidationException {
        var getGlobalVariableDto = globalVariableResourceMapper.mapGetGlobalVariableDto(userId);
        var globalVariables = globalVariableService.getGlobalVariables(getGlobalVariableDto);
        if (CollectionUtils.isEmpty(globalVariables)) {
            return Collections.emptyList();
        }
        return globalVariableResourceMapper.mapEnvironmentModels(globalVariables);
    }

    @POST
    @Path("/globalVariables")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createGlobalVariable(@Valid CreateGlobalVariableModel createGlobalVariableModel) throws ValidationException {
        var createGlobalVariableDto = globalVariableResourceMapper.mapCreateGlobalVariableDto(createGlobalVariableModel);
        var errorMessages = globalVariableService.createGlobalVariable(createGlobalVariableDto);
        return globalVariableResourceMapper.mapResultModel(errorMessages, createGlobalVariableDto);
    }

    @GET
    @Path("/globalVariables/{globalVariableId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GlobalVariableModel getGlobalVariableById(@Valid @PathParam Long globalVariableId) throws ValidationException {
        var getGlobalVariableByIdDto = globalVariableResourceMapper.mapGetGlobalVariableByIdDto(globalVariableId);
        var errorMessages = globalVariableService.getGlobalVariableById(getGlobalVariableByIdDto);
        return globalVariableResourceMapper.mapGlobalVariableModel(getGlobalVariableByIdDto);
    }

    @DELETE
    @Path("/globalVariables/{globalVariableId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteGlobalVariable(@Valid @PathParam Long globalVariableId) throws ValidationException {
        var deleteGlobalVariableDto = globalVariableResourceMapper.mapDeleteGlobalVariableDto(globalVariableId);
        var errorMessages = globalVariableService.deleteGlobalVariable(deleteGlobalVariableDto);
        return Collections.emptyList();
    }

    @PUT
    @Path("/globalVariables/{globalVariableId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateGlobalVariable(@Valid @PathParam Long globalVariableId,
                                                        UpdateGlobalVariableModel updateGlobalVariableModel) throws ValidationException {
        var updateGlobalVariableDto = globalVariableResourceMapper.mapUpdateGlobalVariableDto(globalVariableId, updateGlobalVariableModel);
        var errorMessages = globalVariableService.updateGlobalVariable(updateGlobalVariableDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return CanaUtility.getErrorMessageModels(errorMessages);
        }
        return Collections.emptyList();
    }
}
