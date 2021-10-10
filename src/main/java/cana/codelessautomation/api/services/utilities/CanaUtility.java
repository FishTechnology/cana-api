package cana.codelessautomation.api.services.utilities;

import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CanaUtility {
    public static List<ErrorMessageDto> getErrorMessages(String errorCode) {
        return getErrorMessages(errorCode, "");
    }

    public static List<ErrorMessageDto> getErrorMessages(String errorCode, String message) {
        List<ErrorMessageDto> errorMessageDtos = new ArrayList<>();
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        if (!StringUtils.isEmpty(message)) {
            errorMessageDto.setMessage(message);
        }
        errorMessageDto.setErrorCode(errorCode);
        errorMessageDtos.add(errorMessageDto);
        return errorMessageDtos;
    }

    public static List<ErrorMessageModel> getErrorMessageModels(List<ErrorMessageDto> errorMessages) {
        List<ErrorMessageModel> errorMessageModels = new ArrayList<>();
        for (ErrorMessageDto errorMessageDto : errorMessages) {
            ErrorMessageModel errorMessageModel = new ErrorMessageModel();
            errorMessageModel.setMessage(errorMessageDto.getMessage());
            errorMessageModel.setErrorCode(errorMessageDto.getErrorCode());
            errorMessageModels.add(errorMessageModel);
        }
        return errorMessageModels;
    }
}
