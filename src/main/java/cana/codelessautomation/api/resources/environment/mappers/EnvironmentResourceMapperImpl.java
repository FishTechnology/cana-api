package cana.codelessautomation.api.resources.environment.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.EnvironmentModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EnvironmentResourceMapperImpl implements EnvironmentResourceMapper {
    JMapper<CreateEnvironmentDto, CreateEnvironmentModel> mapperCreateEnvironment;
    JMapper<EnvironmentModel, EnvironmentDao> mapperEnvironmentModel;

    public EnvironmentResourceMapperImpl() {
        mapperCreateEnvironment = new JMapper<>(CreateEnvironmentDto.class, CreateEnvironmentModel.class);
        mapperEnvironmentModel = new JMapper<>(EnvironmentModel.class, EnvironmentDao.class);
    }

    @Override
    public CreateEnvironmentDto mapCreateEnvVariableDto(CreateEnvironmentModel createEnvVariableModel) {
        return mapperCreateEnvironment.getDestination(createEnvVariableModel);
    }

    @Override
    public ResultModel mapResultModel(CreateEnvironmentDto createEnvironmentDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createEnvironmentDto.getId());
        return resultModel;
    }

    @Override
    public List<EnvironmentModel> mapEnvironmentModels(List<EnvironmentDao> environments) {
        List<EnvironmentModel> environmentModels = new ArrayList<>();
        for (EnvironmentDao environment : environments) {
            environmentModels.add(mapperEnvironmentModel.getDestination(environment));
        }
        return environmentModels;
    }

    @Override
    public DeleteEnvironmentDto mapDeleteEnvironmentDto(Long environmentId) {
        DeleteEnvironmentDto deleteEnvironmentDto = new DeleteEnvironmentDto();
        deleteEnvironmentDto.setEnvironmentId(environmentId);
        return deleteEnvironmentDto;
    }
}
