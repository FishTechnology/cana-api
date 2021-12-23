package cana.codelessautomation.api.resources.applicationconfig.service.dto;

import lombok.Data;

@Data
public class DeleteApplicationConfigDto {
    private Long applicationId;
    private Long applicationConfigId;
}
