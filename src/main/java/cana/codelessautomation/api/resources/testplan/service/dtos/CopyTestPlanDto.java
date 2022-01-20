package cana.codelessautomation.api.resources.testplan.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import lombok.Data;

@Data
public class CopyTestPlanDto {
    private Long id;
    private Long testPlanId;
    private String testPlanName;
    private String comments;
    private Long userId;
    private Long applicationId;
    private TestPlanStatusDao status;
    private CustomDetailDao customDetail;
    private TestplanDao testplan;
}
