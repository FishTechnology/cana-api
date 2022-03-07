package cana.codelessautomation.api.resources.globalvariable.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.UIControlOptionDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.UpdateGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class GlobalVariableProcessorMapperImpl implements GlobalVariableProcessorMapper {

    JMapper<GlobalVariableDao, CreateGlobalVariableDto> mapperGlobalVariableDao;

    public GlobalVariableProcessorMapperImpl() {
        // mapperGlobalVariableDao = new JMapper<>(GlobalVariableDao.class, CreateGlobalVariableDto.class);
    }

    @Override
    public GlobalVariableDao mapGlobalVariableDao(CreateGlobalVariableDto createGlobalVariable) {
        GlobalVariableDao globalVariableDao = new GlobalVariableDao();
        globalVariableDao.setFileId(createGlobalVariable.getFileId());
        globalVariableDao.setCreatedBy(createGlobalVariable.getCreatedBy());
        globalVariableDao.setCreatedOn(OffsetDateTime.now());
        globalVariableDao.setIsActive(createGlobalVariable.getIsActive());
        globalVariableDao.setModifiedBy(createGlobalVariable.getModifiedBy());
        globalVariableDao.setModifiedOn(OffsetDateTime.now());
        globalVariableDao.setKey(createGlobalVariable.getKey());
        globalVariableDao.setValue(createGlobalVariable.getValue());
        globalVariableDao.setValueType(createGlobalVariable.getValueType());
        globalVariableDao.setComments(createGlobalVariable.getComments());
        globalVariableDao.setUserId(createGlobalVariable.getUserId());
        return globalVariableDao;
    }

    @Override
    public GlobalVariableDao mapGlobalVariableDao(UpdateGlobalVariableDto updateGlobalVariableDto) {
        GlobalVariableDao globalVariableDao = updateGlobalVariableDto.getGlobalVariable();

        if (!StringUtils.equalsAnyIgnoreCase(updateGlobalVariableDto.getKey(), globalVariableDao.getKey())) {
            globalVariableDao.setKey(updateGlobalVariableDto.getKey());
        }

        if (!StringUtils.equalsAnyIgnoreCase(updateGlobalVariableDto.getValue(), globalVariableDao.getValue())) {
            globalVariableDao.setValue(updateGlobalVariableDto.getValue());
        }

        if (!StringUtils.equalsAnyIgnoreCase(updateGlobalVariableDto.getComments(), globalVariableDao.getComments())) {
            globalVariableDao.setComments(updateGlobalVariableDto.getComments());
        }

        if (updateGlobalVariableDto.getValueType() != updateGlobalVariableDto.getValueType()) {
            globalVariableDao.setValueType(updateGlobalVariableDto.getValueType());
        }

        return globalVariableDao;
    }

    @Override
    public ActionOptionDao mapActionOptionDao(CreateGlobalVariableDto createGlobalVariable, UIControlOptionDto uiControlOptionDto) {
        ActionOptionDao actionOptionDao = new ActionOptionDao();
        actionOptionDao.setActionId(createGlobalVariable.getId());
        actionOptionDao.setOptionType(uiControlOptionDto.getOptionType());
        actionOptionDao.setOrderNumber(uiControlOptionDto.getOrder());
        actionOptionDao.setDuration(uiControlOptionDto.getWaitDuration());
        actionOptionDao.setCreatedBy(createGlobalVariable.getCreatedBy());
        actionOptionDao.setCreatedOn(OffsetDateTime.now());
        actionOptionDao.setIsActive(createGlobalVariable.getIsActive());
        actionOptionDao.setModifiedBy(createGlobalVariable.getModifiedBy());
        actionOptionDao.setModifiedOn(OffsetDateTime.now());
        return actionOptionDao;
    }
}
