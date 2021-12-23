package cana.codelessautomation.api.resources.file.service.errorcode;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileErrorCode extends BaseErrorCode {
    public String getFileStreamContentInValid() {
        return "CanaApi.{0}." + getHttpMethod() + ".File.Content.InValid";
    }
}
