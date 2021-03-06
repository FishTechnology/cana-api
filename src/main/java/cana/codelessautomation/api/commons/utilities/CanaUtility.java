package cana.codelessautomation.api.commons.utilities;

import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
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

    public static List<ErrorMessageModel> getErrorMessageModels(String errorCode) {
        return getErrorMessageModels(errorCode, "");
    }

    public static List<ErrorMessageModel> getErrorMessageModels(String errorCode, String message) {
        List<ErrorMessageModel> errorMsgModels = new ArrayList<>();
        ErrorMessageModel errorMessageModel = new ErrorMessageModel();
        if (!StringUtils.isEmpty(message)) {
            errorMessageModel.setMessage(message);
        }
        errorMessageModel.setErrorCode(errorCode);
        errorMsgModels.add(errorMessageModel);
        return errorMsgModels;
    }
}
