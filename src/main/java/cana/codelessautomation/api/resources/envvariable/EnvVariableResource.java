package cana.codelessautomation.api.resources.envvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.envvariable.mappers.EnvVariableResourceMapper;
import cana.codelessautomation.api.resources.envvariable.models.CreateEnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.models.EnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.models.UpdateEnvVariableModel;
import cana.codelessautomation.api.services.envvariable.EnvVariableService;
import cana.codelessautomation.api.services.utilities.CanaUtility;
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
public class EnvVariableResource {

    @Inject
    EnvVariableService envVariableService;

    @Inject
    EnvVariableResourceMapper envVariableResourceMapper;

    @GET
    @Path("/environments/{environmentId}/envVariables")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<EnvVariableModel> getEnvVariables(@PathParam Long environmentId) throws ValidationException {
        var environmentVariableDaos = envVariableService.getEnvVariables(environmentId);
        return envVariableResourceMapper.mapEnvVariableModels(environmentVariableDaos);
    }

    @GET
    @Path("/environments/{environmentId}/envVariables/{envVariableId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EnvVariableModel getEnvVariableById(@PathParam Long environmentId, @PathParam Long envVariableId) throws ValidationException {
        var deleteEnvVariableDto = envVariableResourceMapper.mapGetEnvVariableByIdDto(environmentId, envVariableId);
        var errors = envVariableService.getEnvVariableById(deleteEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return envVariableResourceMapper.mapEnvVariableModel(deleteEnvVariableDto);
    }


    @DELETE
    @Path("/environments/{environmentId}/envVariables/{envVariableId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteEnvVariable(@QueryParam Long userid,
                                                     @PathParam Long envVariableId,
                                                     @PathParam Long environmentId) throws ValidationException {
        var deleteEnvVariableDto = envVariableResourceMapper.mapDeleteEnvVariableDto(userid, environmentId, envVariableId);
        var errorMessages = envVariableService.deleteEnvVariable(deleteEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return CanaUtility.getErrorMessageModels(errorMessages);
        }
        return Collections.emptyList();
    }

    @POST
    @Path("/environments/{environmentId}/envVariables")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createEnvVariable(
            @Valid @PathParam Long environmentId,
            @Valid CreateEnvVariableModel createEnvVariableModel) throws ValidationException {
        var createEnvVariableDto = envVariableResourceMapper.mapCreateEnvVariableDto(createEnvVariableModel, environmentId);
        var errorMessages = envVariableService.createEnvVariable(createEnvVariableDto);
        return envVariableResourceMapper.mapResultModel(createEnvVariableDto, errorMessages);
    }

    @POST
    @Path("/environments/envVariables/{envVariableId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateEnvVariable(
            @Valid Long environmentId,
            @Valid Long envVariableId,
            @Valid UpdateEnvVariableModel updateEnvVariableModel) throws ValidationException {
        var updateEnvVariableDto = envVariableResourceMapper.mapUpdateEnvVariableDto(updateEnvVariableModel, environmentId, envVariableId);
        var errorMessages = envVariableService.updateEnvVariable(updateEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return CanaUtility.getErrorMessageModels(errorMessages);
        }
        return Collections.emptyList();
    }
}
