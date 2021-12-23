package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleSummaryDto {
    private Long userId;
    private int pageSize;
    private int pageNumber;
    private int totalPageCount;
    private CustomDetailDao customDetail;
    private List<ScheduleDao> scheduleDaos;
}
