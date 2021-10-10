package cana.codelessautomation.api.resources.commonmodels;

import lombok.Data;

@Data
public class ErrorMessageModel {
    private String errorCode;
    private String message;
}
