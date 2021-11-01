package cana.codelessautomation.api.services.action.repositories;

import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionOptionRepository implements PanacheRepository<ActionOptionDao> {
}
