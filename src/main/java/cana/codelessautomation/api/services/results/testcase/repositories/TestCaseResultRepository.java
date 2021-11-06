package cana.codelessautomation.api.services.results.testcase.repositories;

import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestCaseResultRepository implements PanacheRepository<TestCaseResultDao> {
}
