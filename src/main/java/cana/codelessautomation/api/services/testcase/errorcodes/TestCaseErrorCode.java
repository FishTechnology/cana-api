package cana.codelessautomation.api.services.testcase.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestCaseErrorCode extends BaseErrorCode {
    public String getTestCaseNameDuplicate() {
        return "CanaApi.{0}.{1}." + getHttpMethod() + ".TestCase.Name.Duplicated";
    }
}
