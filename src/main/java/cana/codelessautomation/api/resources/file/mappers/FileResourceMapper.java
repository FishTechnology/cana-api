package cana.codelessautomation.api.resources.file.mappers;


import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.file.models.MultipartBodyModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;

import java.util.List;

public interface FileResourceMapper {
    UpdateFileDto mapUpdateFileDto(MultipartBodyModel multipartBodyModel);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, UpdateFileDto updateFileDto);
}
