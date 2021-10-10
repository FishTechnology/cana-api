package cana.codelessautomation.api.resources.environment.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EnvironmentResourceMapperImpl implements EnvironmentResourceMapper {
    JMapper<CreateEnvironmentDto, CreateEnvironmentModel> mapperCreateEnvironment;

    public EnvironmentResourceMapperImpl() {
        mapperCreateEnvironment = new JMapper<>(CreateEnvironmentDto.class, CreateEnvironmentModel.class);
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
}
