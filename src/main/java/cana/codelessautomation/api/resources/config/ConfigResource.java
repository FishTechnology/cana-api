package cana.codelessautomation.api.resources.config;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.mappers.ConfigResourceMapper;
import cana.codelessautomation.api.resources.config.models.ConfigModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigModel;
import cana.codelessautomation.api.resources.config.services.configservice.ConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Path("/api")
@ApplicationScoped
public class ConfigResource {
    @Inject
    ConfigResourceMapper configResourceMapper;

    @Inject
    ConfigService configService;

    @GET
    @Path("/applications/{applicationId}/configs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConfigModel> getConfigsByAppId(@Valid @PathParam Long applicationId) {
        var getConfigsByAppIdDto = configResourceMapper.mapGetConfigsByAppIdDto(applicationId);
        var errorMessages = configService.getConfigsByAppId(getConfigsByAppIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        if (CollectionUtils.isEmpty(getConfigsByAppIdDto.getConfigDaos())) {
            return Collections.emptyList();
        }
        return configResourceMapper.mapConfigModels(getConfigsByAppIdDto);
    }

    @GET
    @Path("/applications/{applicationId}/configs/{configType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConfigModel> getConfigsByConfigType(@Valid @PathParam Long applicationId, @Valid @PathParam String configType) {
        var getConfigsByConfigTypeDto = configResourceMapper.mapGetConfigsByConfigTypeDto(applicationId, configType);
        var errorMessages = configService.getConfigsByConfigType(getConfigsByConfigTypeDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        if (CollectionUtils.isEmpty(getConfigsByConfigTypeDto.getConfigDaos())) {
            return Collections.emptyList();
        }
        return configResourceMapper.mapConfigModels(getConfigsByConfigTypeDto);
    }

    @GET
    @Path("/applications/{applicationId}/configs/{configType}/{configId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ConfigModel getConfigById(@Valid @PathParam Long applicationId, @Valid @PathParam String configType, @Valid @PathParam String configId) {
        var getConfigByIdDto = configResourceMapper.mapGetConfigByIdDto(applicationId, configId, configType);
        var errorMessages = configService.getConfigById(getConfigByIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        if (Objects.isNull(getConfigByIdDto.getConfigDao())) {
            return null;
        }
        return configResourceMapper.mapConfigModel(getConfigByIdDto);
    }


    @POST
    @Path("/applications/{applicationId}/configs/{configType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createConfig(@Valid @PathParam Long applicationId,
                                    @Valid CreateConfigModel createConfigModel,
                                    @Valid @PathParam String configType) {
        var createConfigDto = configResourceMapper.mapCreateConfigDto(applicationId, createConfigModel, configType);
        var errorMessages = configService.createConfig(createConfigDto);
        return configResourceMapper.mapResultModel(errorMessages, createConfigDto);
    }
}
