package cana.codelessautomation.api.resources.system.service.processors.mappers;

import cana.codelessautomation.api.resources.system.service.repositories.daos.SystemConfigDao;

import java.util.List;

public interface SystemConfigProcessorMapper {
    List<SystemConfigDao> mapSystemConfigDao(Long applicationId, Long userId);

    SystemConfigDao mapSystemConfigDao(String key, String value, Long applicationId, Long userId);
}
