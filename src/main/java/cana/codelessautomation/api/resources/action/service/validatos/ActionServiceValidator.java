package cana.codelessautomation.api.resources.action.service.validatos;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;

import java.util.List;

public interface ActionServiceValidator {
    List<ErrorMessageDto> validateCreateAction(CreateActionDto createActionDto);
}
