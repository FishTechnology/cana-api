package cana.codelessautomation.api.resources.testcase.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestPlanAndTestCaseGroupErrorCode extends BaseErrorCode {

    public String getGroupingNotFoundErrorCode() {
        return "CanaApi.{0}.{1}." + getHttpMethod() + ".TestPlanAndTestCase.Group.Id.NotFound";
    }
}
