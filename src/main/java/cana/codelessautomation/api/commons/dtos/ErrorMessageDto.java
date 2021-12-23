package cana.codelessautomation.api.commons.dtos;

import lombok.Data;

@Data
public class ErrorMessageDto {
    private String errorCode;
    private String message;
}
