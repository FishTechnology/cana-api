package cana.codelessautomation.api.resources.globalvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.globalvariable.models.CreateGlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.GlobalVariableModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.DeleteGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableByIdDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GlobalVariableResourceMapperImpl implements GlobalVariableResourceMapper {

    JMapper<GlobalVariableModel, GlobalVariableDao> mapperGlobalVariableModel;

    public GlobalVariableResourceMapperImpl() {
        mapperGlobalVariableModel = new JMapper<>(GlobalVariableModel.class, GlobalVariableDao.class);
    }

    @Override
    public List<GlobalVariableModel> mapEnvironmentModels(List<GlobalVariableDao> globalVariables) {
        List<GlobalVariableModel> globalVariableModels = new ArrayList<>();
        for (GlobalVariableDao globalVariableDao : globalVariables) {
            globalVariableModels.add(mapperGlobalVariableModel.getDestination(globalVariableDao));
        }
        return globalVariableModels;
    }

    @Override
    public GetGlobalVariableDto mapGetGlobalVariableDto(Long userId) {
        GetGlobalVariableDto getGlobalVariableDto = new GetGlobalVariableDto();
        getGlobalVariableDto.setUserId(userId);
        return getGlobalVariableDto;
    }

    @Override
    public CreateGlobalVariableDto mapCreateGlobalVariableDto(CreateGlobalVariableModel createGlobalVariableModel) {
        return null;
    }

    @Override
    public ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateGlobalVariableDto createGlobalVariable) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createGlobalVariable.getId());
        return resultModel;
    }

    @Override
    public GetGlobalVariableByIdDto mapGetGlobalVariableByIdDto(Long globalVariableId) {
        GetGlobalVariableByIdDto globalVariableByIdDto = new GetGlobalVariableByIdDto();
        globalVariableByIdDto.setGlobalVariableId(globalVariableId);
        return globalVariableByIdDto;
    }

    @Override
    public GlobalVariableModel mapGlobalVariableModel(GetGlobalVariableByIdDto getGlobalVariableByIdDto) {
        return mapperGlobalVariableModel.getDestination(getGlobalVariableByIdDto.getGlobalVariable());
    }

    @Override
    public DeleteGlobalVariableDto mapDeleteGlobalVariableDto(Long globalVariableId) {
        DeleteGlobalVariableDto deleteGlobalVariableDto = new DeleteGlobalVariableDto();
        deleteGlobalVariableDto.setGlobalVariableId(globalVariableId);
        return deleteGlobalVariableDto;
    }
}
