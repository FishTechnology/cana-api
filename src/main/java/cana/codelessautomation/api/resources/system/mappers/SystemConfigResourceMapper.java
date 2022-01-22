package cana.codelessautomation.api.resources.system.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.system.models.GetSystemConfigsByAppIdModel;
import cana.codelessautomation.api.resources.system.models.SystemConfigModel;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;
import cana.codelessautomation.api.resources.system.service.repositories.daos.SystemConfigDao;

import java.util.List;

public interface SystemConfigResourceMapper {
    GetSystemConfigsByAppIdDto mapSystemConfigResourceMapper();

    GetSystemConfigsByAppIdModel mapSystemConfigModel(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto, List<ErrorMessageDto> errors);

    SystemConfigModel mapSystemConfigModel(SystemConfigDao SystemConfigDao);
}
