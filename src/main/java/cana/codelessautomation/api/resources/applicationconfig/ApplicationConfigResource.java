package cana.codelessautomation.api.resources.applicationconfig;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.applicationconfig.mappers.ApplicationConfigResourceMapper;
import cana.codelessautomation.api.resources.applicationconfig.models.ApplicationConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.CreateAppConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.UpdateApplicationConfigModel;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.applicationconfig.service.ApplicationConfigService;
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
public class ApplicationConfigResource {

    @Inject
    ApplicationConfigResourceMapper appConfigResourceMapper;

    @Inject
    ApplicationConfigService applicationConfigService;


    @POST
    @Path("/applications/{applicationId}/applicationConfigs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createApplicationConfig(@Valid @PathParam Long applicationId,
                                               @Valid CreateAppConfigModel createAppConfigModel) throws ValidationException {
        var createAppConfigDto = appConfigResourceMapper.mapCreateAppConfigDto(applicationId, createAppConfigModel);
        var errorMessages = applicationConfigService.createApplicationConfig(createAppConfigDto);
        return appConfigResourceMapper.mapResultModel(createAppConfigDto, errorMessages);
    }

    @GET
    @Path("/applications/{applicationId}/applicationConfigs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ApplicationConfigModel> getApplicationConfigs(@Valid @PathParam Long applicationId,
                                                              @Valid @QueryParam Long userId) throws ValidationException {
        var getApplicationConfigsDto = appConfigResourceMapper.mapGetApplicationConfigsDto(applicationId, userId);
        var applicationConfigDaos = applicationConfigService.getApplicationConfigs(getApplicationConfigsDto);
        if (CollectionUtils.isEmpty(applicationConfigDaos)) {
            return Collections.emptyList();
        }
        return appConfigResourceMapper.mapApplicationConfigModels(applicationConfigDaos);
    }

    @DELETE
    @Path("/applications/{applicationId}/applicationConfigs/{applicationConfigId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteApplicationConfig(@Valid @PathParam Long applicationId,
                                                           @Valid @PathParam Long applicationConfigId) throws ValidationException {
        var deleteApplicationConfigDto = appConfigResourceMapper.mapDeleteApplicationConfigDto(applicationId, applicationConfigId);
        var errorMessages = applicationConfigService.deleteApplicationConfig(deleteApplicationConfigDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }

    @PUT
    @Path("/applications/{applicationId}/applicationConfigs/{applicationConfigId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateApplicationConfig(@Valid @PathParam Long applicationId,
                                                           @Valid @PathParam Long applicationConfigId,
                                                           @Valid UpdateApplicationConfigModel updateApplicationConfigmodel) throws ValidationException {
        var updateApplicationConfigDto = appConfigResourceMapper.mapUpdateApplicationConfigDto(applicationId, applicationConfigId, updateApplicationConfigmodel);
        var errorMessages = applicationConfigService.updateApplicationConfig(updateApplicationConfigDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }
}
