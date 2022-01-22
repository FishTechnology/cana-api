package cana.codelessautomation.api.resources.system.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SystemConfigVerifierImpl implements SystemConfigVerifier {
    @Inject
    ApplicationVerifier applicationVerifier;

    @Override
    public List<ErrorMessageDto> verifyGetSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto) {
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto) {
        var response = applicationVerifier.isApplicationIdValid(getSystemConfigsByAppIdDto.getApplicationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        getSystemConfigsByAppIdDto.setApplicationDao(response.getValue());
        return Collections.emptyList();
    }
}
