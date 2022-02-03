package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.validator;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.SystemVariableEnum;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.errorcodes.ConfigKeyValueServiceErrorCode;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ConfigKeyValueServiceValidatorImpl implements ConfigKeyValueServiceValidator {
    @Inject
    ConfigKeyValueServiceErrorCode configKeyValueServiceErrorCode;

    @Override
    public List<ErrorMessageDto> validateCreateConfig(CreateConfigKeyValueDto createConfigKeyValueDto) {
        return isKeyValid(createConfigKeyValueDto);
    }

    @Override
    public List<ErrorMessageDto> isKeyValid(CreateConfigKeyValueDto createConfigKeyValueDto) {
        if (!createConfigKeyValueDto.getIsApplicationVariable()) {
            return Collections.emptyList();
        }

        if (EnumUtils.isValidEnumIgnoreCase(SystemVariableEnum.class, createConfigKeyValueDto.getKey())) {
            return Collections.emptyList();
        }

        return CanaUtility.getErrorMessages(configKeyValueServiceErrorCode.getInValidSystemVariable());
    }
}
