package cana.codelessautomation.api.services.file.repositories;

import cana.codelessautomation.api.services.file.repositories.daos.FileDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileRepository implements PanacheRepository<FileDao> {
}
