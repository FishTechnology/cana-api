package cana.codelessautomation.api.resources.application.mappers;

import cana.codelessautomation.api.resources.application.models.ApplicationModel;
import cana.codelessautomation.api.resources.application.models.CreateAppModel;
import cana.codelessautomation.api.resources.application.models.UpdateApplicationModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.application.service.dtos.CreateApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ApplicationResourceMapperImpl implements ApplicationResourceMapper {
    @Override
    public CreateApplicationDto mapCreateAppDto(CreateAppModel createAppModel) {
        CreateApplicationDto createApplicationDto = new CreateApplicationDto();
        createApplicationDto.setName(createAppModel.getName());
        createApplicationDto.setUserId(createAppModel.getUserId());
        createApplicationDto.setComments(createAppModel.getComments());
        return createApplicationDto;
    }

    @Override
    public ResultModel mapResultModel(CreateApplicationDto createApplicationDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createApplicationDto.getId().longValue());
        return resultModel;
    }

    @Override
    public List<ApplicationModel> mapApplicationModels(List<ApplicationDao> applicationDaos) {
        List<ApplicationModel> applicationModels = new ArrayList<>();
        for (ApplicationDao applicationDao : applicationDaos) {
            ApplicationModel applicationModel = new ApplicationModel();
            applicationModel.setId(applicationDao.getId().toString());
            applicationModel.setName(applicationDao.getName());
            applicationModel.setComments(applicationDao.getComments());
            applicationModel.setCreatedBy(applicationDao.getCreatedBy());
            applicationModel.setModifiedBy(applicationDao.getModifiedBy());
            applicationModel.setCreatedOn(applicationDao.getCreatedOn().toString());
            applicationModel.setModifiedOn(applicationDao.getModifiedOn().toString());
            applicationModel.setIsActive(applicationDao.getIsActive());
            applicationModel.setUserId(applicationDao.getUserId().toString());
            applicationModels.add(applicationModel);
        }
        return applicationModels;
    }

    @Override
    public DeleteApplicationDto mapDeleteApplicationDto(Long applicationId) {
        DeleteApplicationDto deleteApplicationDto = new DeleteApplicationDto();
        deleteApplicationDto.setApplicationId(applicationId);
        return deleteApplicationDto;
    }

    @Override
    public UpdateApplicationDto mapUpdateApplicationDto(Long applicationId, UpdateApplicationModel updateApplicationModel) {
        UpdateApplicationDto updateApplicationDto = new UpdateApplicationDto();
        updateApplicationDto.setApplicationId(applicationId);
        updateApplicationDto.setComments(updateApplicationModel.getComments());
        updateApplicationDto.setName(updateApplicationModel.getName());
        updateApplicationDto.setComments(updateApplicationModel.getComments());
        updateApplicationDto.setUserId(updateApplicationModel.getUserId());
        return updateApplicationDto;
    }

    @Override
    public ApplicationModel mapApplicationModel(ApplicationDao applicationDao) {
        ApplicationModel applicationModel = new ApplicationModel();
        applicationModel.setId(applicationDao.getId().toString());
        applicationModel.setName(applicationDao.getName());
        applicationModel.setComments(applicationDao.getComments());
        applicationModel.setCreatedBy(applicationDao.getCreatedBy());
        applicationModel.setModifiedBy(applicationDao.getModifiedBy());
        applicationModel.setCreatedOn(applicationDao.getCreatedOn().toString());
        applicationModel.setModifiedOn(applicationDao.getModifiedOn().toString());
        applicationModel.setIsActive(applicationDao.getIsActive());
        applicationModel.setUserId(applicationDao.getUserId().toString());
        return applicationModel;
    }
}
