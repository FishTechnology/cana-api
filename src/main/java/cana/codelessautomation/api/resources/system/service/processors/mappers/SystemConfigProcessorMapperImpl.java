package cana.codelessautomation.api.resources.system.service.processors.mappers;

import cana.codelessautomation.api.resources.system.service.repositories.daos.SystemConfigDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SystemConfigProcessorMapperImpl implements SystemConfigProcessorMapper {
    @Override
    public List<SystemConfigDao> mapSystemConfigDao(Long applicationId, Long userId) {
        List<SystemConfigDao> systemConfigDaos = new ArrayList<>();
        SystemConfigDao systemConfigDao = mapSystemConfigDao("IS_PARALLEL_EXECUTION_ENABLE", "false", applicationId, userId);
        systemConfigDaos.add(systemConfigDao);
        return systemConfigDaos;
    }

    @Override
    public SystemConfigDao mapSystemConfigDao(String key, String value, Long applicationId, Long userId) {
        SystemConfigDao systemConfigDao = new SystemConfigDao();
        systemConfigDao.setKey(key);
        systemConfigDao.setValue(value);
        systemConfigDao.setApplicationId(applicationId);
        systemConfigDao.setIsActive(true);
        systemConfigDao.setCreatedOn(OffsetDateTime.now());
        systemConfigDao.setModifiedOn(OffsetDateTime.now());
        systemConfigDao.setUserId(userId);
        return systemConfigDao;
    }
}
