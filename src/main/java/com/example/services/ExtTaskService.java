package com.example.services;

import com.example.facade.GetExtTaskListFacade;
import com.example.models.common.ExtTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtTaskService {

    private final GetExtTaskListFacade getExtTaskListFacade;

    public List<ExtTaskDto> getTaskList() {
        return getExtTaskListFacade.getExtTaskList(false);
    }

    public List<ExtTaskDto> getAllTaskList() {
        return getExtTaskListFacade.getExtTaskList(true);
    }

    public void deleteExtTask(String id) {
        getExtTaskListFacade.deleteExtTask(id);
    }
}
