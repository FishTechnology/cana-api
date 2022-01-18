package cana.codelessautomation.api.resources.system.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.system.models.GetSystemConfigsByAppIdModel;
import cana.codelessautomation.api.resources.system.models.SystemConfigModel;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;
import cana.codelessautomation.api.resources.system.service.repositories.daos.SystemConfigDao;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SystemConfigResourceMapperImpl implements SystemConfigResourceMapper {
    @Override
    public GetSystemConfigsByAppIdDto mapSystemConfigResourceMapper(Long applicationId) {
        GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto = new GetSystemConfigsByAppIdDto();
        getSystemConfigsByAppIdDto.setApplicationId(applicationId);
        return getSystemConfigsByAppIdDto;
    }

    @Override
    public GetSystemConfigsByAppIdModel mapSystemConfigModel(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto, List<ErrorMessageDto> errors) {
        GetSystemConfigsByAppIdModel getSystemConfigsByAppIdModel = new GetSystemConfigsByAppIdModel();
        if (CollectionUtils.isNotEmpty(errors)) {
            getSystemConfigsByAppIdModel.setErrors(CanaUtility.getErrorMessageModels(errors));
            return getSystemConfigsByAppIdModel;
        }

        List<SystemConfigModel> systemConfigs = new ArrayList<>();
        for (SystemConfigDao systemConfigDao : getSystemConfigsByAppIdDto.getSystemConfigDao()) {
            var systemConfigModel = mapSystemConfigModel(systemConfigDao);
            systemConfigs.add(systemConfigModel);
        }
        return getSystemConfigsByAppIdModel;
    }

    @Override
    public SystemConfigModel mapSystemConfigModel(SystemConfigDao systemConfigDao) {
        SystemConfigModel systemConfigModel = new SystemConfigModel();
        systemConfigModel.setId(systemConfigDao.getId().toString());
        systemConfigModel.setKey(systemConfigDao.getKey());
        systemConfigModel.setValue(systemConfigDao.getValue());
        systemConfigModel.setComments(systemConfigDao.getComments());
        systemConfigModel.setIsActive(systemConfigDao.getIsActive());
        systemConfigModel.setUserid(systemConfigDao.getUserId().toString());
        systemConfigModel.setCreatedBy(systemConfigDao.getCreatedBy());
        systemConfigModel.setCreatedOn(systemConfigDao.getCreatedOn().toString());
        systemConfigModel.setModifiedBy(systemConfigDao.getModifiedBy());
        systemConfigModel.setModifiedOn(systemConfigDao.getModifiedOn().toString());
        systemConfigModel.setApplicationId(systemConfigDao.getApplicationId().toString());
        return systemConfigModel;
    }
}
