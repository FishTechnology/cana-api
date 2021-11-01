package cana.codelessautomation.api.services.action;

import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;

public interface ActionService {
    List<ErrorMessageDto> createAction(CreateActionDto createActionDto);
}
