package cana.codelessautomation.api.resources.file.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.file.models.MultipartBodyModel;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FileResourceMapperImpl implements FileResourceMapper {
    @Override
    public UpdateFileDto mapUpdateFileDto(MultipartBodyModel multipartBodyModel) {
        UpdateFileDto updateFileDto = new UpdateFileDto();
        updateFileDto.setFile(multipartBodyModel.getFile());
        updateFileDto.setFileName(multipartBodyModel.getFileName());
        updateFileDto.setUserId(multipartBodyModel.getUserId());
        updateFileDto.setFileSize(multipartBodyModel.getFileSize());
        return updateFileDto;
    }

    @Override
    public ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, UpdateFileDto updateFileDto) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(updateFileDto.getId());
        return resultModel;
    }
}
