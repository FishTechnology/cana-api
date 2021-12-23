package cana.codelessautomation.api.resources.environment.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.DeleteEnvironmentsModel;
import cana.codelessautomation.api.resources.environment.models.EnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.UpdateEnvironmentModel;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentsDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
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
        // mapperCreateEnvironment = new JMapper<>(CreateEnvironmentDto.class, CreateEnvironmentModel.class);
        //mapperEnvironmentModel = new JMapper<>(EnvironmentModel.class, EnvironmentDao.class);
    }

    @Override
    public CreateEnvironmentDto mapCreateEnvVariableDto(CreateEnvironmentModel createEnvVariableModel) {
        CreateEnvironmentDto createEnvironment = new CreateEnvironmentDto();
        createEnvironment.setName(createEnvVariableModel.getName());
        createEnvironment.setComments(createEnvVariableModel.getComments());
        createEnvironment.setUserId(createEnvVariableModel.getUserId());
        return createEnvironment;
        // return mapperCreateEnvironment.getDestination(createEnvVariableModel);
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
            EnvironmentModel environmentModel = new EnvironmentModel();
            environmentModel.setId(environment.getId());
            environmentModel.setName(environment.getName());
            environmentModel.setUserId(environment.getUserId());
            environmentModel.setIsActive(environment.getIsActive());
            environmentModel.setCreatedOn(environment.getCreatedOn().toString());
            environmentModel.setModifiedOn(environment.getModifiedOn().toString());
            environmentModel.setCreatedBy(environment.getCreatedBy());
            environmentModel.setModifiedBy(environment.getModifiedBy());
            // environmentModels.add(mapperEnvironmentModel.getDestination(environment));
            environmentModels.add(environmentModel);
        }
        return environmentModels;
    }

    @Override
    public DeleteEnvironmentDto mapDeleteEnvironmentDto(Long environmentId) {
        DeleteEnvironmentDto deleteEnvironmentDto = new DeleteEnvironmentDto();
        deleteEnvironmentDto.setEnvironmentId(environmentId);
        return deleteEnvironmentDto;
    }

    @Override
    public DeleteEnvironmentsDto mapDeleteEnvironmentsDto(DeleteEnvironmentsModel deleteEnvironmentsModel) {
        DeleteEnvironmentsDto deleteEnvironments = new DeleteEnvironmentsDto();
        deleteEnvironments.setEnvironmentIds(deleteEnvironmentsModel.getEnvironmentIds());
        deleteEnvironments.setUserId(deleteEnvironmentsModel.getUserId());
        return deleteEnvironments;
    }

    @Override
    public EnvironmentModel mapEnvironmentModel(EnvironmentDao environment) {
        EnvironmentModel environmentModel = new EnvironmentModel();
        environmentModel.setId(environment.getId());
        environmentModel.setName(environment.getName());
        environmentModel.setComments(environment.getComments());
        environmentModel.setCreatedBy(environment.getCreatedBy());
        environmentModel.setModifiedBy(environment.getModifiedBy());
        environmentModel.setModifiedOn(environment.getModifiedOn().toString());
        environmentModel.setCreatedOn(environment.getCreatedOn().toString());
        return environmentModel;
    }

    @Override
    public UpdateEnvironmentDto mapUpdateEnvironmentDto(UpdateEnvironmentModel updateEnvVariableModel, Long environmentId) {
        UpdateEnvironmentDto updateEnvironment = new UpdateEnvironmentDto();
        updateEnvironment.setId(environmentId);
        updateEnvironment.setName(updateEnvVariableModel.getName());
        updateEnvironment.setComments(updateEnvVariableModel.getComments());
        updateEnvironment.setUserId(updateEnvVariableModel.getUserId());
        return updateEnvironment;
    }
}
