package cana.codelessautomation.api.resources.envvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.PageSetDetailModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.envvariable.mappers.EnvVariableResourceMapper;
import cana.codelessautomation.api.resources.envvariable.models.CreateEnvVariableModel;
import cana.codelessautomation.api.services.envvariable.EnvVariableService;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
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
    @Path("/envvariables/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PageSetDetailModel getEnvVariables(@PathParam long userid,
                                              @QueryParam int pageNumber,
                                              @QueryParam int pageSize) throws ValidationException {
        var pageSetDetailDto = envVariableService.getEnvVariables(userid, pageNumber, pageSize);
        return envVariableResourceMapper.mapPageSetDetailModel(pageSetDetailDto);
    }


    @DELETE
    @Path("/envvariables/{envVariableId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ErrorMessageModel> deleteEnvVariable(@QueryParam Long userid,
                                                     @PathParam Long envVariableId) throws ValidationException {
        var deleteEnvVariableDto = envVariableResourceMapper.mapDeleteEnvVariableDto(userid, envVariableId);
        var errorMessages = envVariableService.deleteEnvVariable(deleteEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return CanaUtility.getErrorMessageModels(errorMessages);
        }
        return Collections.emptyList();
    }

    @POST
    @Path("/envvariables")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultModel createEnvVariable(CreateEnvVariableModel createEnvVariableModel) throws ValidationException {
        var createEnvVariableDto = envVariableResourceMapper.mapCreateEnvVariableDto(createEnvVariableModel);
        var errorMessages = envVariableService.createEnvVariable(createEnvVariableDto);
        return envVariableResourceMapper.mapResultModel(createEnvVariableDto, errorMessages);
    }
}
