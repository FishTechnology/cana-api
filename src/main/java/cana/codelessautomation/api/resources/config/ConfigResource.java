package cana.codelessautomation.api.resources.config;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.mappers.ConfigResourceMapper;
import cana.codelessautomation.api.resources.config.models.ConfigModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigModel;
import cana.codelessautomation.api.resources.config.service.ConfigService;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/configs")
@ApplicationScoped
public class ConfigResource {
    @Inject
    ConfigResourceMapper configResourceMapper;

    @Inject
    ConfigService configService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConfigModel> getConfigsByUserId(@QueryParam("userId") String userId) {
        var getConfigsByUserIdDto = configResourceMapper.mapGetConfigsByUserIdDto(userId);
        var errorMessages = configService.getConfigsByUserId(getConfigsByUserIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        // return configResourceMapper.mapConfigModels(getConfigsByUserIdDto);
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultModel createConfig(@Valid CreateConfigModel createConfigModel) {
        var createConfigDto = configResourceMapper.mapCreateConfigDto(createConfigModel);
        var errorMessages = configService.createConfig(createConfigDto);
        return configResourceMapper.mapResultModel(errorMessages, createConfigDto);
    }
}
