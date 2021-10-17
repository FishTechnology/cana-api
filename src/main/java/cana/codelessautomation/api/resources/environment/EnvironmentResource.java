package cana.codelessautomation.api.resources.environment;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.mappers.EnvironmentResourceMapper;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.EnvironmentModel;
import cana.codelessautomation.api.services.environment.EnvironmentService;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
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
    @Path("/environments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultModel createEnvironment(@Valid CreateEnvironmentModel createEnvVariableModel) throws ValidationException {
        var createEnvironmentDto = environmentResourceMapper.mapCreateEnvVariableDto(createEnvVariableModel);
        var errorMessages = environmentService.createEnvironment(createEnvironmentDto);
        return environmentResourceMapper.mapResultModel(createEnvironmentDto, errorMessages);
    }

    @GET
    @Path("/environments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<EnvironmentModel> getEnvironments(@QueryParam Long userId) throws ValidationException {
        var environments = environmentService.getEnvironments(userId);
        if(CollectionUtils.isEmpty(environments)){
            return Collections.emptyList();
        }
        return environmentResourceMapper.mapEnvironmentModels(environments);
    }

    @DELETE
    @Path("/environments/{environmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ErrorMessageModel> deleteEnvironment(@PathParam Long environmentId) throws ValidationException {
        var deleteEnvironment =environmentResourceMapper.mapDeleteEnvironmentDto(environmentId);
        var errorMessage = environmentService.deleteEnvironment(deleteEnvironment);
        if(CollectionUtils.isEmpty(errorMessage)){
            return Collections.emptyList();
        }
       return CanaUtility.getErrorMessageModels(errorMessage);
    }
}
