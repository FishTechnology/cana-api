package cana.codelessautomation.api.resources.environment.models;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class DeleteEnvironmentsModel {
    @Valid
    List<Long> environmentIds;
    String userId;
}
