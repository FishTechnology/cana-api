package cana.codelessautomation.api.services.action.processors;

import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;

public interface ActionServiceProcessor {
    List<ErrorMessageDto> processCreateAction(CreateActionDto createActionDto);

    List<ErrorMessageDto> createActionOption(CreateActionDto createActionDto);

    List<ErrorMessageDto> createAction(CreateActionDto createActionDto);
}
