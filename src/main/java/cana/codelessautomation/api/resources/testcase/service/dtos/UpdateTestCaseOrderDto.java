package cana.codelessautomation.api.resources.testcase.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import lombok.Data;

import java.util.List;

@Data
public class UpdateTestCaseOrderDto {
    private Long testPlanId;
    private Long userId;
    private List<TestCaseOrderDto> TestCaseOrderDtos;
    private CustomDetailDao customDetail;
    private TestplanDao testplan;
    private Long applicationId;
}
