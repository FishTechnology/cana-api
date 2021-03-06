package cana.codelessautomation.api.commons.dtos;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BaseDto {
    private Long userId;
    private OffsetDateTime modifiedOn;
    private OffsetDateTime createdOn;
    private String createdBy;
    private String modifiedBy;
}
