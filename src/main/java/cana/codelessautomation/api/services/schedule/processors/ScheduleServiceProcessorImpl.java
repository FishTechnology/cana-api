package cana.codelessautomation.api.services.schedule.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.processors.mappers.ScheduleServiceProcessorMapper;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleIterationRepository;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleRepository;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ScheduleServiceProcessorImpl implements ScheduleServiceProcessor {

    @Inject
    ScheduleServiceProcessorMapper scheduleServiceProcessorMapper;

    @Inject
    ScheduleRepository scheduleRepository;

    @Inject
    ScheduleIterationRepository scheduleIterationRepository;

    @Override
    public List<ErrorMessageDto> processCreateSchedule(CreateScheduleDto createScheduleDto) {
        var errors = createSchedule(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return createScheduleIteration(createScheduleDto);
    }


    @Override
    public List<ErrorMessageDto> processGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        return getScheduleSummary(scheduleSummaryDto);
    }

    @Override
    public List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        var panacheQuery = scheduleRepository.findByUserIdPage(scheduleSummaryDto.getUserId());
        var scheduleDaos = panacheQuery.page(scheduleSummaryDto.getPageNumber(), scheduleSummaryDto.getPageSize()).list();
        scheduleSummaryDto.setTotalPageCount(panacheQuery.pageCount());
        scheduleSummaryDto.setScheduleDaos(scheduleDaos);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createScheduleIteration(CreateScheduleDto createScheduleDto) {
        var scheduleIterationDao = scheduleServiceProcessorMapper.mapScheduleIterationDao(createScheduleDto);
        scheduleIterationRepository.persist(scheduleIterationDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto) {
        var scheduleDao = scheduleServiceProcessorMapper.mapScheduleDao(createScheduleDto);
        scheduleRepository.persist(scheduleDao);
        createScheduleDto.setId(scheduleDao.getId());
        return Collections.emptyList();
    }
}
