package cana.codelessautomation.api.resources.envvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.PageSetDetailModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.envvariable.models.CreateEnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.models.EnvVariableModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EnvVariableResourceMapperImpl implements EnvVariableResourceMapper {
    JMapper<EnvVariableModel, EnvironmentVariableDao> mapperEnvVariableModel;
    JMapper<CreateEnvVariableDto, CreateEnvVariableModel> mapperCreateEnvVariable;

    public EnvVariableResourceMapperImpl() {
        mapperEnvVariableModel = new JMapper<>(EnvVariableModel.class, EnvironmentVariableDao.class);
        mapperCreateEnvVariable = new JMapper<>(CreateEnvVariableDto.class, CreateEnvVariableModel.class);
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
    public DeleteEnvVariableDto mapDeleteEnvVariableDto(Long userid, Long envVariableId) {
        DeleteEnvVariableDto deleteEnvVariableDto = new DeleteEnvVariableDto();
        deleteEnvVariableDto.setUserid(userid);
        deleteEnvVariableDto.setEnvVariableId(envVariableId);
        return deleteEnvVariableDto;
    }

    @Override
    public CreateEnvVariableDto mapCreateEnvVariableDto(CreateEnvVariableModel createEnvVariableModel) {
        return mapperCreateEnvVariable.getDestination(createEnvVariableModel);
    }

    @Override
    public ResultModel mapResultModel(CreateEnvVariableDto createEnvVariableDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createEnvVariableDto.getId());
        return resultModel;
    }
}
