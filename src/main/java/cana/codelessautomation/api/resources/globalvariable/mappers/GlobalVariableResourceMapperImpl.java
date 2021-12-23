package cana.codelessautomation.api.resources.globalvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.globalvariable.models.CreateGlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.GlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.UIControlOptionModel;
import cana.codelessautomation.api.resources.globalvariable.models.UpdateGlobalVariableModel;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.*;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableType;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
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
            globalVariableModel.setValueType(globalVariableDao.getValueType().name());
            globalVariableModel.setFileId(globalVariableDao.getFileId());
            if (globalVariableDao.getFileDaos() != null) {
                globalVariableModel.setContent(globalVariableDao.getFileDaos().getContent());
                globalVariableModel.setFileId(globalVariableDao.getFileDaos().getId());
                globalVariableModel.setValue(globalVariableDao.getFileDaos().getFileName());
            }
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
        createGlobalVariable.setFileId(createGlobalVariableModel.getFileId());
        createGlobalVariable.setUserId(createGlobalVariableModel.getUserId());
        if (CollectionUtils.isEmpty(createGlobalVariableModel.getUiControlOptions())) {
            return createGlobalVariable;
        }

        for (UIControlOptionModel uiControlOptionModel : createGlobalVariableModel.getUiControlOptions()) {
            createGlobalVariable.getUiControlOptions().add(mapUIControlOptionDto(uiControlOptionModel));
        }

        return createGlobalVariable;
    }

    @Override
    public UIControlOptionDto mapUIControlOptionDto(UIControlOptionModel uiControlOption) {
        UIControlOptionDto uiControlOptionDto = new UIControlOptionDto();
        uiControlOptionDto.setOptionType(EnumUtils.getEnumIgnoreCase(ActionOptionTypeDao.class, uiControlOption.getOptionType()));
        uiControlOptionDto.setOrder(uiControlOption.getOrder());
        uiControlOptionDto.setWaitDuration(uiControlOption.getWaitDuration());
        return uiControlOptionDto;
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
        globalVariableModel.setFileId(globalVariableDao.getFileId());
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
