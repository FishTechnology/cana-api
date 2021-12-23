package cana.codelessautomation.api.resources.testplan.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.testplan.service.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import lombok.Data;

@Data
public class UpdateTestplanStatusModel {
    private Long userId;
    @ValidEnumString(enumRef = TestPlanStatusDao.class,message = TestplanErrorCode.getTestPlanStatusIsInValid)
    private String status;
}
