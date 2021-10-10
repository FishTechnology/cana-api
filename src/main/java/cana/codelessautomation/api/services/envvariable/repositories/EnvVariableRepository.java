package cana.codelessautomation.api.services.envvariable.repositories;

import cana.codelessautomation.api.services.customer.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

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

    public void deleteByEnvId(Long envVariableId) {
        update("isactive=false WHERE id = ?1", envVariableId);
    }

    public EnvironmentVariableDao findByUserIdAndKey(Long userId, String key) {
        return find("key = ?1 and and userid = ?2 and isactive=true", key, userId).firstResult();
    }
}
