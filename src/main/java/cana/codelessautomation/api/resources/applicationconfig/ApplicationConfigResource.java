package cana.codelessautomation.api.resources.applicationconfig;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.applicationconfig.mappers.ApplicationConfigResourceMapper;
import cana.codelessautomation.api.resources.applicationconfig.models.ApplicationConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.CreateAppConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.UpdateApplicationConfigModel;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.applicationconfig.ApplicationConfigService;
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
import java.util.UUID;

@Path("/api")
public class ApplicationConfigResource {

    @Inject
    ApplicationConfigResourceMapper appConfigResourceMapper;

    @Inject
    ApplicationConfigService applicationConfigService;


    @POST
    @Path("/applicationConfigs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createApplicationConfig(@Valid CreateAppConfigModel createAppConfigModel) throws ValidationException {
        var createAppConfigDto = appConfigResourceMapper.mapCreateAppConfigDto(createAppConfigModel);
        var errorMessages = applicationConfigService.createApplicationConfig(createAppConfigDto);
        return appConfigResourceMapper.mapResultModel(createAppConfigDto, errorMessages);
    }

    @GET
    @Path("/applicationConfigs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ApplicationConfigModel> getApplicationConfigs(@Valid @QueryParam String userId) throws ValidationException {
        var applicationConfigDaos = applicationConfigService.getApplicationConfigs(userId);
        if (CollectionUtils.isEmpty(applicationConfigDaos)) {
            return Collections.emptyList();
        }
        return appConfigResourceMapper.mapApplicationConfigModels(applicationConfigDaos);
    }

    @DELETE
    @Path("/applicationConfigs/{applicationConfigId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteApplicationConfig(@Valid @PathParam UUID applicationConfigId) throws ValidationException {
        var errorMessages = applicationConfigService.deleteApplicationConfig(applicationConfigId);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }

    @PUT
    @Path("/applicationConfigs/{applicationConfigId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateApplicationConfig(@Valid @PathParam UUID applicationConfigId,
                                                           @Valid UpdateApplicationConfigModel updateApplicationConfigmodel) throws ValidationException {
        var updateApplicationConfigDto = appConfigResourceMapper.mapCreateAppConfigDto(applicationConfigId, updateApplicationConfigmodel);
        var errorMessages = applicationConfigService.updateApplicationConfig(updateApplicationConfigDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }
}
