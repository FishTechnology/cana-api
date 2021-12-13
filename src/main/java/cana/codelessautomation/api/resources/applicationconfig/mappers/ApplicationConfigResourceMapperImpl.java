package cana.codelessautomation.api.resources.applicationconfig.mappers;

import cana.codelessautomation.api.resources.applicationconfig.models.ApplicationConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.CreateAppConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.UpdateApplicationConfigModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ApplicationConfigResourceMapperImpl implements ApplicationConfigResourceMapper {
    @Override
    public CreateAppConfigDto mapCreateAppConfigDto(CreateAppConfigModel createAppConfigModel) {
        CreateAppConfigDto createAppConfigDto = new CreateAppConfigDto();
        createAppConfigDto.setKey(createAppConfigModel.getKey());
        createAppConfigDto.setValue(createAppConfigModel.getValue());
        createAppConfigDto.setUserId(createAppConfigModel.getUserId());
        return createAppConfigDto;
    }

    @Override
    public ResultModel mapResultModel(CreateAppConfigDto createAppConfigDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setUuId(createAppConfigDto.getId());
        return resultModel;
    }

    @Override
    public List<ApplicationConfigModel> mapApplicationConfigModels(List<ApplicationConfigDao> applicationConfigDaos) {
        List<ApplicationConfigModel> appConfigModels = new ArrayList<>();
        for (ApplicationConfigDao applicationConfigDao : applicationConfigDaos) {
            ApplicationConfigModel AppConfigModel = new ApplicationConfigModel();
            AppConfigModel.setId(applicationConfigDao.getId());
            AppConfigModel.setKey(applicationConfigDao.getKey());
            AppConfigModel.setValue(applicationConfigDao.getValue());
            AppConfigModel.setComments(applicationConfigDao.getComments());
            AppConfigModel.setCreatedBy(applicationConfigDao.getCreatedBy());
            AppConfigModel.setModifiedBy(applicationConfigDao.getModifiedBy());
            AppConfigModel.setCreatedOn(applicationConfigDao.getCreatedOn().toString());
            AppConfigModel.setModifiedOn(applicationConfigDao.getModifiedOn().toString());
            AppConfigModel.setIsActive(applicationConfigDao.getIsActive());
            AppConfigModel.setUserId(Long.valueOf(applicationConfigDao.getUserId()));
            appConfigModels.add(AppConfigModel);
        }
        return appConfigModels;
    }

    @Override
    public UpdateApplicationConfigDto mapCreateAppConfigDto(UUID applicationConfigId, UpdateApplicationConfigModel updateApplicationConfigmodel) {
        UpdateApplicationConfigDto updateApplicationConfigDto = new UpdateApplicationConfigDto();
        updateApplicationConfigDto.setId(applicationConfigId);
        updateApplicationConfigDto.setKey(updateApplicationConfigmodel.getKey());
        updateApplicationConfigDto.setValue(updateApplicationConfigmodel.getValue());
        updateApplicationConfigDto.setComments(updateApplicationConfigmodel.getComments());
        updateApplicationConfigDto.setUserId(updateApplicationConfigmodel.getUserId());
        return updateApplicationConfigDto;
    }
}
