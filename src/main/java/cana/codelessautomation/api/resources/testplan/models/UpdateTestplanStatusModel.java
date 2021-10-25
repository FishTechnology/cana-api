package cana.codelessautomation.api.resources.testplan.models;

import lombok.Data;

@Data
public class UpdateTestplanStatusModel {
    private Long userId;
    private String status;
}
