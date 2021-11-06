package cana.codelessautomation.api.services.results.action.repositories;

import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionResultRepository implements PanacheRepository<ActionResultDao> {
}
