package cana.codelessautomation.api.resources.globalvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.globalvariable.models.CreateGlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.GlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.UIControlOptionModel;
import cana.codelessautomation.api.resources.globalvariable.models.UpdateGlobalVariableModel;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.*;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;

import java.util.List;

public interface GlobalVariableResourceMapper {
    List<GlobalVariableModel> mapEnvironmentModels(List<GlobalVariableDao> globalVariables);

    GetGlobalVariableDto mapGetGlobalVariableDto(Long userId);

    CreateGlobalVariableDto mapCreateGlobalVariableDto(CreateGlobalVariableModel createGlobalVariableModel);

    UIControlOptionDto mapUIControlOptionDto(UIControlOptionModel uiControlOption);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateGlobalVariableDto createGlobalVariableDto);

    GetGlobalVariableByIdDto mapGetGlobalVariableByIdDto(Long globalVariableId);

    GlobalVariableModel mapGlobalVariableModel(GetGlobalVariableByIdDto getGlobalVariableByIdDto);

    DeleteGlobalVariableDto mapDeleteGlobalVariableDto(Long globalVariableId);

    UpdateGlobalVariableDto mapUpdateGlobalVariableDto(Long globalVariableId, UpdateGlobalVariableModel updateGlobalVariableModel);
}
