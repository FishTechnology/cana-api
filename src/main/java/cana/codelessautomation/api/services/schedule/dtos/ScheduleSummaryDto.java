package cana.codelessautomation.api.services.schedule.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
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
