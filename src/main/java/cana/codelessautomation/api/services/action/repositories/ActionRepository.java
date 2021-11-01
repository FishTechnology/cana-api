package cana.codelessautomation.api.services.action.repositories;

import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionRepository implements PanacheRepository<ActionDao>  {
}
