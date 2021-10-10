package cana.codelessautomation.api.services.common.dtos;

import lombok.Data;

@Data
public class ErrorMessageDto {
    private String errorCode;
    private String message;
}
