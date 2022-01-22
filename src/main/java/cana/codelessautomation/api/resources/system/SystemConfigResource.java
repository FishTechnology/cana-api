package cana.codelessautomation.api.resources.system;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.system.mappers.SystemConfigResourceMapper;
import cana.codelessautomation.api.resources.system.models.GetSystemConfigsByAppIdModel;
import cana.codelessautomation.api.resources.system.service.SystemConfigService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class SystemConfigResource {
    @Inject
    SystemConfigService systemConfigService;

    @Inject
    SystemConfigResourceMapper systemConfigResourceMapper;

    @GET
    @Path("/systemConfigs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public GetSystemConfigsByAppIdModel getSystemConfigsByAppId() throws ValidationException {
        var getSystemConfigsByAppIdDto = systemConfigResourceMapper.mapSystemConfigResourceMapper();
        var errors = systemConfigService.getSystemConfigsByAppId(getSystemConfigsByAppIdDto);
        return systemConfigResourceMapper.mapSystemConfigModel(getSystemConfigsByAppIdDto, errors);
    }
}
