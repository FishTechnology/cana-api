package cana.codelessautomation.api.resources.environment.service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DeleteEnvironmentsDto {
    List<Long> environmentIds;
    String userId;
}
