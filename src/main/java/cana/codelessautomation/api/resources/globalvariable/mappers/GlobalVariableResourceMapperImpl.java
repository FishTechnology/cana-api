package cana.codelessautomation.api.resources.globalvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.globalvariable.models.CreateGlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.GlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.UpdateGlobalVariableModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.globalvariable.dtos.*;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableType;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GlobalVariableResourceMapperImpl implements GlobalVariableResourceMapper {

    JMapper<GlobalVariableModel, GlobalVariableDao> mapperGlobalVariableModel;

    public GlobalVariableResourceMapperImpl() {
        //mapperGlobalVariableModel = new JMapper<>(GlobalVariableModel.class, GlobalVariableDao.class);
    }

    @Override
    public List<GlobalVariableModel> mapEnvironmentModels(List<GlobalVariableDao> globalVariables) {
        List<GlobalVariableModel> globalVariableModels = new ArrayList<>();
        for (GlobalVariableDao globalVariableDao : globalVariables) {
            var globalVariableModel = new GlobalVariableModel();
            globalVariableModel.setId(globalVariableDao.getId());
            globalVariableModel.setUserId(globalVariableDao.getUserId());
            globalVariableModel.setComments(globalVariableDao.getComments());
            globalVariableModel.setCreatedBy(globalVariableDao.getCreatedBy());
            globalVariableModel.setCreatedOn(globalVariableDao.getCreatedOn());
            globalVariableModel.setModifiedBy(globalVariableDao.getModifiedBy());
            globalVariableModel.setModifiedOn(globalVariableDao.getModifiedOn());
            globalVariableModel.setValue(globalVariableDao.getValue());
            globalVariableModel.setKey(globalVariableDao.getKey());
            globalVariableModel.setContent(globalVariableDao.getContent());
            globalVariableModels.add(globalVariableModel);
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
        CreateGlobalVariableDto createGlobalVariable = new CreateGlobalVariableDto();
        createGlobalVariable.setKey(createGlobalVariableModel.getKey());
        createGlobalVariable.setValue(createGlobalVariableModel.getValue());
        createGlobalVariable.setComments(createGlobalVariableModel.getComments());
        createGlobalVariable.setValueType(EnumUtils.getEnumIgnoreCase(GlobalVariableType.class, createGlobalVariableModel.getValueType()));
        //createGlobalVariable.setContent(createGlobalVariableModel.getContent());
        createGlobalVariable.setUserId(createGlobalVariableModel.getUserId());
        return createGlobalVariable;
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
        var globalVariableDao = getGlobalVariableByIdDto.getGlobalVariable();
        var globalVariableModel = new GlobalVariableModel();
        globalVariableModel.setId(globalVariableDao.getId());
        globalVariableModel.setUserId(globalVariableDao.getUserId());
        globalVariableModel.setComments(globalVariableDao.getComments());
        globalVariableModel.setCreatedBy(globalVariableDao.getCreatedBy());
        globalVariableModel.setCreatedOn(globalVariableDao.getCreatedOn());
        globalVariableModel.setModifiedBy(globalVariableDao.getModifiedBy());
        globalVariableModel.setModifiedOn(globalVariableDao.getModifiedOn());
        globalVariableModel.setValue(globalVariableDao.getValue());
        globalVariableModel.setKey(globalVariableDao.getKey());
        globalVariableModel.setContent(globalVariableDao.getContent());
        globalVariableModel.setValueType(globalVariableDao.getValueType().name());
        return globalVariableModel;
    }

    @Override
    public DeleteGlobalVariableDto mapDeleteGlobalVariableDto(Long globalVariableId) {
        DeleteGlobalVariableDto deleteGlobalVariableDto = new DeleteGlobalVariableDto();
        deleteGlobalVariableDto.setGlobalVariableId(globalVariableId);
        return deleteGlobalVariableDto;
    }

    @Override
    public UpdateGlobalVariableDto mapUpdateGlobalVariableDto(Long globalVariableId, UpdateGlobalVariableModel updateGlobalVariableModel) {
        UpdateGlobalVariableDto updateGlobalVariable = new UpdateGlobalVariableDto();
        updateGlobalVariable.setGlobalVariableId(globalVariableId);
        updateGlobalVariable.setKey(updateGlobalVariableModel.getKey());
        updateGlobalVariable.setValue(updateGlobalVariableModel.getValue());
        updateGlobalVariable.setComments(updateGlobalVariableModel.getComments());
        updateGlobalVariable.setValueType(EnumUtils.getEnumIgnoreCase(GlobalVariableType.class, updateGlobalVariableModel.getValueType()));
        updateGlobalVariable.setUserId(updateGlobalVariableModel.getUserId());
        return updateGlobalVariable;
    }
}
