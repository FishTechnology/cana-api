package cana.codelessautomation.api.resources.system.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;
import cana.codelessautomation.api.resources.system.service.processors.mappers.SystemConfigProcessorMapper;
import cana.codelessautomation.api.resources.system.service.repositories.SystemConfigRepository;
import cana.codelessautomation.api.resources.system.service.repositories.daos.SystemConfigDao;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SystemConfigProcessorImpl implements SystemConfigProcessor {
    @Inject
    SystemConfigRepository systemConfigRepository;

    @Inject
    SystemConfigProcessorMapper systemConfigProcessorMapper;

    @Override
    public List<ErrorMessageDto> processorGetSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto) {
        return getSystemConfigsByAppId(getSystemConfigsByAppIdDto);
    }

    @Override
    public List<ErrorMessageDto> getSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto) {
        var systemConfigDaos = systemConfigRepository.findByAppId();
        if (CollectionUtils.isNotEmpty(systemConfigDaos)) {
            getSystemConfigsByAppIdDto.setSystemConfigDao(systemConfigDaos);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createInitialConfig(Long applicationId, Long userId) {
        var systemConfigDaos = systemConfigProcessorMapper.mapSystemConfigDao(applicationId, userId);
        for (SystemConfigDao systemConfigDao : systemConfigDaos) {
            systemConfigRepository.persist(systemConfigDao);
        }
        return Collections.emptyList();
    }
}
