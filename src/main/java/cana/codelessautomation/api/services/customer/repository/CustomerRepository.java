package cana.codelessautomation.api.services.customer.repository;

import cana.codelessautomation.api.services.customer.dtos.enums.CustomerStatus;
import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomDetailDao> {
    public CustomDetailDao findByUserId(long userId) {
        return find("id", userId).firstResult();
    }

    public CustomDetailDao GetCustomerPageSet(long userId) {
       return find("id = ?1 and status = ?2",userId, CustomerStatus.INACTIVE).firstResult();
    }
}
