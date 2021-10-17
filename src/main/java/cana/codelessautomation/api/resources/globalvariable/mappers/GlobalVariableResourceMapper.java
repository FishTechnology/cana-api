package cana.codelessautomation.api.resources.globalvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.globalvariable.models.CreateGlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.GlobalVariableModel;
import cana.codelessautomation.api.resources.globalvariable.models.UpdateGlobalVariableModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.globalvariable.dtos.*;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;

import java.util.List;

public interface GlobalVariableResourceMapper {
    List<GlobalVariableModel> mapEnvironmentModels(List<GlobalVariableDao> globalVariables);

    GetGlobalVariableDto mapGetGlobalVariableDto(Long userId);

    CreateGlobalVariableDto mapCreateGlobalVariableDto(CreateGlobalVariableModel createGlobalVariableModel);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateGlobalVariableDto createGlobalVariableDto);

    GetGlobalVariableByIdDto mapGetGlobalVariableByIdDto(Long globalVariableId);

    GlobalVariableModel mapGlobalVariableModel(GetGlobalVariableByIdDto getGlobalVariableByIdDto);

    DeleteGlobalVariableDto mapDeleteGlobalVariableDto(Long globalVariableId);

    UpdateGlobalVariableDto mapUpdateGlobalVariableDto(Long globalVariableId, UpdateGlobalVariableModel updateGlobalVariableModel);
}
