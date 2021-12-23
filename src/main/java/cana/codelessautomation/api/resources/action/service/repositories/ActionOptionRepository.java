package cana.codelessautomation.api.resources.action.service.repositories;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionOptionRepository implements PanacheRepository<ActionOptionDao> {
}
