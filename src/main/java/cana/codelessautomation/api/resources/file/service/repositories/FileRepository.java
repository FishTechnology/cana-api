package cana.codelessautomation.api.resources.file.service.repositories;

import cana.codelessautomation.api.resources.file.service.repositories.daos.FileDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileRepository implements PanacheRepository<FileDao> {
}
