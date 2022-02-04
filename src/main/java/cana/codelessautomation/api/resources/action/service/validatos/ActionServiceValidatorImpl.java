package cana.codelessautomation.api.resources.action.service.validatos;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ActionServiceValidatorImpl implements ActionServiceValidator {
    @Override
    public List<ErrorMessageDto> validateCreateAction(CreateActionDto createActionDto) {
        return Collections.emptyList();
    }
}
