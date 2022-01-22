package cana.codelessautomation.api.resources.environment;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.mappers.EnvironmentResourceMapper;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.EnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.UpdateEnvironmentModel;
import cana.codelessautomation.api.resources.environment.service.EnvironmentService;
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
public class EnvironmentResource {

    @Inject
    EnvironmentResourceMapper environmentResourceMapper;

    @Inject
    EnvironmentService environmentService;

    @POST
    @Path("/applications/{applicationId}/environments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createEnvironment(@Valid Long applicationId, @Valid CreateEnvironmentModel createEnvVariableModel) throws ValidationException {
        var createEnvironmentDto = environmentResourceMapper.mapCreateEnvVariableDto(applicationId, createEnvVariableModel);
        var errorMessages = environmentService.createEnvironment(createEnvironmentDto);
        return environmentResourceMapper.mapResultModel(createEnvironmentDto, errorMessages);
    }

    @PUT
    @Path("/applications/{applicationId}/environments/{environmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateEnvironment(@Valid UpdateEnvironmentModel updateEnvVariableModel,
                                                     @Valid @PathParam Long environmentId) throws ValidationException {
        var updateEnvironmentDto = environmentResourceMapper.mapUpdateEnvironmentDto(updateEnvVariableModel, environmentId);
        var errorMessages = environmentService.updateEnvironment(updateEnvironmentDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }

    @GET
    @Path("/applications/{applicationId}/environments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<EnvironmentModel> getEnvironments(@Valid @QueryParam Long userId) throws ValidationException {
        var environments = environmentService.getEnvironments(userId);
        if (CollectionUtils.isEmpty(environments)) {
            return Collections.emptyList();
        }
        return environmentResourceMapper.mapEnvironmentModels(environments);
    }

    @GET
    @Path("/applications/{applicationId}/environments/{environmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EnvironmentModel getEnvironmentById(@Valid @PathParam Long environmentId) throws ValidationException {
        var environment = environmentService.getEnvironment(environmentId);
        if (environment == null) {
            return null;
        }
        return environmentResourceMapper.mapEnvironmentModel(environment);
    }

    @DELETE
    @Path("/applications/{applicationId}/environments/{environmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteEnvironment(@Valid @PathParam Long environmentId) throws ValidationException {
        var deleteEnvironment = environmentResourceMapper.mapDeleteEnvironmentDto(environmentId);
        var errorMessage = environmentService.deleteEnvironment(deleteEnvironment);
        if (CollectionUtils.isEmpty(errorMessage)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessage);
    }
}
