package cana.codelessautomation.api.resources.config.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.service.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.service.dtos.GetConfigsByUserIdDto;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ConfigServiceVerifierImpl implements ConfigServiceVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Override
    public List<ErrorMessageDto> verifyGetConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        return isUserIdValid(getConfigsByUserIdDto);
    }

    @Override
    public List<ErrorMessageDto> verifyCreateConfig(CreateConfigDto createConfigDto) {
        return isUserIdValid(createConfigDto);
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateConfigDto createConfigDto) {
        var response = customerServiceVerifier.isUserIdValid(createConfigDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createConfigDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        var response = customerServiceVerifier.isUserIdValid(getConfigsByUserIdDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        getConfigsByUserIdDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }
}
