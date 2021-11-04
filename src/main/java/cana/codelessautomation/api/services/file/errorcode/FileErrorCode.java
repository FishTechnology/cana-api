package cana.codelessautomation.api.services.file.errorcode;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileErrorCode extends BaseErrorCode {
    public String getFileStreamContentInValid() {
        return "CanaApi.{0}." + getHttpMethod() + ".File.Content.InValid";
    }
}
