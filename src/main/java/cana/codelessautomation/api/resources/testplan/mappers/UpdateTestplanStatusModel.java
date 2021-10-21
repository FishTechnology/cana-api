package cana.codelessautomation.api.resources.testplan.mappers;

import lombok.Data;

@Data
public class UpdateTestplanStatusModel {
    private Long userId;
    private String status;
}
