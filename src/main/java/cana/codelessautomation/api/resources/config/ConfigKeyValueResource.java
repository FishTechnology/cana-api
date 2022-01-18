package cana.codelessautomation.api.resources.config;


import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.mappers.ConfigKeyValueResourceMapper;
import cana.codelessautomation.api.resources.config.models.ConfigKeyValueModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigKeyValueModel;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.ConfigKeyValueService;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/applications/{applicationId}")
@ApplicationScoped
public class ConfigKeyValueResource {

    @Inject
    ConfigKeyValueResourceMapper configKeyValueResourceMapper;

    @Inject
    ConfigKeyValueService configKeyValueService;

    @POST
    @Path("/configs/{configType}/{configId}/keyValues")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createConfigKeyValue(@Valid @PathParam Long applicationId, @Valid @PathParam String configType, @Valid @PathParam String configId, @Valid CreateConfigKeyValueModel createConfigKeyValue) {
        var createConfigKeyValueDto = configKeyValueResourceMapper.mapCreateConfigKeyValueDto(applicationId, configType, configId, createConfigKeyValue);
        var errorMessages = configKeyValueService.createConfigKeyValue(createConfigKeyValueDto);
        return configKeyValueResourceMapper.mapResultModel(errorMessages, createConfigKeyValueDto);
    }

    @GET
    @Path("/configs/{configId}/keyValues")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ConfigKeyValueModel> getConfigKeyValue(@Valid @PathParam Long configId) {
        var getConfigKeyValueDto = configKeyValueResourceMapper.mapGetConfigKeyValueDto(configId);
        var errorMessages = configKeyValueService.getConfigKeyValue(getConfigKeyValueDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return configKeyValueResourceMapper.mapConfigKeyValueModel(getConfigKeyValueDto);
    }
}
