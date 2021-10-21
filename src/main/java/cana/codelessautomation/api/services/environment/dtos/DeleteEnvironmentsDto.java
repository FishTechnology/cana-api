package cana.codelessautomation.api.services.environment.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DeleteEnvironmentsDto {
    List<Long> environmentIds;
    String userId;
}
