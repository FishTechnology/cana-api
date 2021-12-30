package cana.codelessautomation.api.resources.envvariable.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.PageSetDetailModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.customer.service.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.resources.envvariable.models.CreateEnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.models.EnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.models.UpdateEnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.service.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.GetEnvVariableByIdDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.UpdateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableType;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EnvVariableResourceMapperImpl implements EnvVariableResourceMapper {
    JMapper<EnvVariableModel, EnvironmentVariableDao> mapperEnvVariableModel;
    JMapper<CreateEnvVariableDto, CreateEnvVariableModel> mapperCreateEnvVariable;

    public EnvVariableResourceMapperImpl() {
        //   mapperEnvVariableModel = new JMapper<>(EnvVariableModel.class, EnvironmentVariableDao.class);
        //  mapperCreateEnvVariable = new JMapper<>(CreateEnvVariableDto.class, CreateEnvVariableModel.class);
    }

    @Override
    public PageSetDetailModel mapPageSetDetailModel(EnvPageSetDetailDto pageSetDetailDtos) {
        PageSetDetailModel pageSetDetailModel = new PageSetDetailModel();
        pageSetDetailModel.setPageSize(pageSetDetailDtos.getPageSize());
        pageSetDetailModel.setPageNumber(pageSetDetailDtos.getPageNumber());
        if (CollectionUtils.isEmpty(pageSetDetailDtos.getEnvironmentVariables())) {
            return pageSetDetailModel;
        }
        List<EnvVariableModel> envVariableModels = new ArrayList<>();
        for (EnvironmentVariableDao environmentVariable : pageSetDetailDtos.getEnvironmentVariables()) {
            envVariableModels.add(mapperEnvVariableModel.getDestination(environmentVariable));
        }
        pageSetDetailModel.setItems(envVariableModels);

        return pageSetDetailModel;
    }

    @Override
    public DeleteEnvVariableDto mapDeleteEnvVariableDto(Long userid, Long environmentId, Long envVariableId) {
        DeleteEnvVariableDto deleteEnvVariableDto = new DeleteEnvVariableDto();
        deleteEnvVariableDto.setUserid(userid);
        deleteEnvVariableDto.setEnvVariableId(envVariableId);
        deleteEnvVariableDto.setEnvironmentId(environmentId);
        return deleteEnvVariableDto;
    }

    @Override
    public CreateEnvVariableDto mapCreateEnvVariableDto(CreateEnvVariableModel createEnvVariableModel, Long environmentId) {
        CreateEnvVariableDto createEnvVariable = new CreateEnvVariableDto();
        createEnvVariable.setKey(createEnvVariableModel.getKey());
        createEnvVariable.setValue(createEnvVariableModel.getValue());
        createEnvVariable.setComments(createEnvVariableModel.getComments());
        createEnvVariable.setType(EnumUtils.getEnumIgnoreCase(EnvironmentVariableType.class, createEnvVariableModel.getType()));
        createEnvVariable.setUserId(createEnvVariableModel.getUserId());
        createEnvVariable.setEnvironmentId(environmentId);
        return createEnvVariable;
    }

    @Override
    public ResultModel mapResultModel(CreateEnvVariableDto createEnvVariableDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createEnvVariableDto.getId().toString());
        return resultModel;
    }

    @Override
    public List<EnvVariableModel> mapEnvVariableModels(List<EnvironmentVariableDao> environmentVariableDaos) {
        List<EnvVariableModel> envVariableModels = new ArrayList<>();
        for (EnvironmentVariableDao environmentVariableDao : environmentVariableDaos) {
            EnvVariableModel envVariableModel = new EnvVariableModel();
            envVariableModel.setComments(environmentVariableDao.getComments());
            envVariableModel.setEnvironmentId(environmentVariableDao.getEnvironmentId());
            envVariableModel.setKey(environmentVariableDao.getKey());
            envVariableModel.setValue(environmentVariableDao.getValue());
            envVariableModel.setCreatedBy(environmentVariableDao.getCreatedBy());
            envVariableModel.setCreatedOn(environmentVariableDao.getCreatedOn());
            envVariableModel.setModifiedBy(environmentVariableDao.getModifiedBy());
            envVariableModel.setModifiedOn(environmentVariableDao.getModifiedOn());
            envVariableModel.setIsActive(environmentVariableDao.getIsActive());
            envVariableModel.setType(environmentVariableDao.getType().name());
            envVariableModel.setUserId(environmentVariableDao.getUserId());
            envVariableModel.setId(environmentVariableDao.getId());
            envVariableModels.add(envVariableModel);
        }
        return envVariableModels;
    }

    @Override
    public UpdateEnvVariableDto mapUpdateEnvVariableDto(UpdateEnvVariableModel updateEnvVariableModel, Long environmentId, Long envVariableId) {
        UpdateEnvVariableDto updateEnvVariable = new UpdateEnvVariableDto();
        return updateEnvVariable;
    }

    @Override
    public EnvVariableModel mapEnvVariableModel(EnvironmentVariableDao environmentVariableDao) {
        EnvVariableModel envVariableModel = new EnvVariableModel();
        envVariableModel.setComments(environmentVariableDao.getComments());
        envVariableModel.setEnvironmentId(environmentVariableDao.getEnvironmentId());
        envVariableModel.setKey(environmentVariableDao.getKey());
        envVariableModel.setValue(environmentVariableDao.getValue());
        envVariableModel.setCreatedBy(environmentVariableDao.getCreatedBy());
        envVariableModel.setCreatedOn(environmentVariableDao.getCreatedOn());
        envVariableModel.setModifiedBy(environmentVariableDao.getModifiedBy());
        envVariableModel.setModifiedOn(environmentVariableDao.getModifiedOn());
        envVariableModel.setIsActive(environmentVariableDao.getIsActive());
        envVariableModel.setId(environmentVariableDao.getId());
        return envVariableModel;
    }

    @Override
    public GetEnvVariableByIdDto mapGetEnvVariableByIdDto(Long environmentId, Long envVariableId) {
        GetEnvVariableByIdDto getEnvVariableByIdDto = new GetEnvVariableByIdDto();
        getEnvVariableByIdDto.setEnvVariableId(envVariableId);
        getEnvVariableByIdDto.setEnvVariableId(environmentId);
        return getEnvVariableByIdDto;
    }

    @Override
    public EnvVariableModel mapEnvVariableModel(GetEnvVariableByIdDto deleteEnvVariableDto) {
        var environmentVariableDao = deleteEnvVariableDto.getEnvironmentVariable();
        EnvVariableModel envVariableModel = new EnvVariableModel();
        envVariableModel.setComments(environmentVariableDao.getComments());
        envVariableModel.setEnvironmentId(environmentVariableDao.getEnvironmentId());
        envVariableModel.setKey(environmentVariableDao.getKey());
        envVariableModel.setValue(environmentVariableDao.getValue());
        envVariableModel.setCreatedBy(environmentVariableDao.getCreatedBy());
        envVariableModel.setCreatedOn(environmentVariableDao.getCreatedOn());
        envVariableModel.setModifiedBy(environmentVariableDao.getModifiedBy());
        envVariableModel.setModifiedOn(environmentVariableDao.getModifiedOn());
        envVariableModel.setIsActive(environmentVariableDao.getIsActive());
        envVariableModel.setId(environmentVariableDao.getId());
        return envVariableModel;
    }
}
