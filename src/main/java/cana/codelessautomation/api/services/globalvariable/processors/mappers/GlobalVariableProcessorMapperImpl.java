package cana.codelessautomation.api.services.globalvariable.processors.mappers;

import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionDao;
import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.UIControlOptionDto;
import cana.codelessautomation.api.services.globalvariable.dtos.UpdateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;
import com.googlecode.jmapper.JMapper;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;

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
        globalVariableDao.setCreatedOn(createGlobalVariable.getCreatedOn());
        globalVariableDao.setIsActive(createGlobalVariable.getIsActive());
        globalVariableDao.setModifiedBy(createGlobalVariable.getModifiedBy());
        globalVariableDao.setModifiedOn(createGlobalVariable.getModifiedOn());
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
        actionOptionDao.setWaitDuration(uiControlOptionDto.getWaitDuration());
        actionOptionDao.setCreatedBy(createGlobalVariable.getCreatedBy());
        actionOptionDao.setCreatedOn(createGlobalVariable.getCreatedOn());
        actionOptionDao.setIsActive(createGlobalVariable.getIsActive());
        actionOptionDao.setModifiedBy(createGlobalVariable.getModifiedBy());
        actionOptionDao.setModifiedOn(createGlobalVariable.getModifiedOn());
        return actionOptionDao;
    }
}
