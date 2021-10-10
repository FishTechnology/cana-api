package cana.codelessautomation.api.resources.environment;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.mappers.EnvironmentResourceMapper;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.services.environment.EnvironmentService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public ResultModel createEnvironment(CreateEnvironmentModel createEnvVariableModel) throws ValidationException {
        var createEnvironmentDto = environmentResourceMapper.mapCreateEnvVariableDto(createEnvVariableModel);
        var errorMessages = environmentService.createEnvironment(createEnvironmentDto);
        return environmentResourceMapper.mapResultModel(createEnvironmentDto, errorMessages);
    }
}
