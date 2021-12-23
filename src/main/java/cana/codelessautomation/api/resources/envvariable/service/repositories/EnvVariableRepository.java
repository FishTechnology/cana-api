package cana.codelessautomation.api.resources.envvariable.service.repositories;

import cana.codelessautomation.api.resources.customer.service.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class EnvVariableRepository implements PanacheRepository<EnvironmentVariableDao> {

    public EnvPageSetDetailDto GetEnvPageSet(long userId, int pageNumber, int pageSize) {
        EnvPageSetDetailDto envPageSetDetailDto = new EnvPageSetDetailDto();
        var environmentVariables = find("userid = ?1", userId);
        envPageSetDetailDto.setPageSize(pageSize);
        envPageSetDetailDto.setPageNumber(pageNumber);
        envPageSetDetailDto.setTotalPageCount(environmentVariables.count());
        if (envPageSetDetailDto.getTotalPageCount() > 0) {
            envPageSetDetailDto.setEnvironmentVariables(environmentVariables.page(pageNumber, pageSize).list());
        }
        return envPageSetDetailDto;
    }

    public EnvironmentVariableDao findByIdAndIsActive(Long envVariableId) {
        return find("id = ?1 and isactive=true", envVariableId).firstResult();
    }

    public void deleteByEnvId(Long userId, Long environmentId, Long envVariableId) {
        update("isactive=false , modifiedOn = ?1 , modifiedBy = ?2  WHERE id = ?3 and environmentId = ?4",
                OffsetDateTime.now(),
                userId,
                envVariableId,
                environmentId);
    }

    public EnvironmentVariableDao findByUserIdAndKeyAndEnvId(Long userId, String key, Long environmentId) {
        return find("key = ?1 and  userid = ?2 and isactive=true and environmentId = ?3", key, userId, environmentId).firstResult();
    }

    public List<EnvironmentVariableDao> findByEnvId(long environmentId) {
        return list("environmentId = ?1 and isactive=true", environmentId);
    }
}
