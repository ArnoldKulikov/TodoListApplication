package com.example.services.task.imp;

import com.example.exeption.MyException;
import com.example.facade.GetExtTaskListFacade;
import com.example.models.common.ExtTaskDto;
import com.example.services.task.CommonTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtTaskServiceImp implements CommonTaskService {

    private final GetExtTaskListFacade getExtTaskListFacade;

    public List<ExtTaskDto> getTaskList() {
        return getExtTaskListFacade.getExtTaskList(false);
    }

    public List<ExtTaskDto> getAllTaskList() {
        return getExtTaskListFacade.getExtTaskList(true);
    }

    @Override
    public void deleteTask(String id) throws MyException {
        getExtTaskListFacade.deleteExtTask(id.substring(4));
    }
}
