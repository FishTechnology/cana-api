package cana.codelessautomation.api.resources.system.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;
import cana.codelessautomation.api.resources.system.service.processors.SystemConfigProcessor;
import cana.codelessautomation.api.resources.system.service.verifiers.SystemConfigVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class SystemConfigServiceImpl implements SystemConfigService {
    @Inject
    SystemConfigVerifier systemConfigVerifier;

    @Inject
    SystemConfigProcessor systemConfigProcessor;

    @Override
    public List<ErrorMessageDto> getSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto) {
        var errors = systemConfigVerifier.verifyGetSystemConfigsByAppId(getSystemConfigsByAppIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return systemConfigProcessor.processorGetSystemConfigsByAppId(getSystemConfigsByAppIdDto);
    }
}
