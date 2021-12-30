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
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

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
    @Path("/configs/{configType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConfigModel> getConfigsByUserId(@Valid @PathParam String configType, @QueryParam String userId) {
        var getConfigsByUserIdDto = configResourceMapper.mapGetConfigsByUserIdDto(userId, configType);
        var errorMessages = configService.getConfigsByUserId(getConfigsByUserIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        if (CollectionUtils.isEmpty(getConfigsByUserIdDto.getConfigDaos())) {
            return Collections.emptyList();
        }
        return configResourceMapper.mapConfigModels(getConfigsByUserIdDto);
    }

    @GET
    @Path("/configs/{configType}/{configId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ConfigModel getConfigById(@Valid @PathParam String configType, @Valid @PathParam String configId) {
        var getConfigByIdDto = configResourceMapper.mapGetConfigByIdDto(configId, configType);
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
    @Path("/configs/{configType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createConfig(@Valid CreateConfigModel createConfigModel) {
        var createConfigDto = configResourceMapper.mapCreateConfigDto(createConfigModel);
        var errorMessages = configService.createConfig(createConfigDto);
        return configResourceMapper.mapResultModel(errorMessages, createConfigDto);
    }
}
