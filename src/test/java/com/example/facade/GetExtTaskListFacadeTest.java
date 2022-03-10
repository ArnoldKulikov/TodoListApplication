package com.example.facade;

import com.example.models.common.ExtTaskDto;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest
class GetExtTaskListFacadeTest {

    private GetExtTaskListFacade getExtTaskListFacade = new GetExtTaskListFacade();

    @Test
    void getExtTaskList(WireMockRuntimeInfo wireMockRuntimeInfo) {
        WireMock wireMock = wireMockRuntimeInfo.getWireMock();
        wireMock.register(WireMock.get("http://localhost:8080/task?all=false").willReturn(okJson("{\"tasks\": [{\"id\": 3,\"description\": \"task3\",\"done\": false}]}")));

        List<ExtTaskDto> extTaskDto = getExtTaskListFacade.getExtTaskList();

        assertEquals(1, extTaskDto.size());
    }

    @Test
    void getAllExtTaskList(WireMockRuntimeInfo wireMockRuntimeInfo) {
        WireMock wireMock = wireMockRuntimeInfo.getWireMock();
        wireMock.register(WireMock.get("http://localhost:8080/task?all=true").willReturn(okJson("{\"tasks\":[{\"id\":3,\"description\":\"task3\",\"done\":false},{\"id\":2,\"description\":\"task2\",\"done\":true}]}")));

        List<ExtTaskDto> extTaskDto = getExtTaskListFacade.getAllExtTaskList();

        assertEquals(2, extTaskDto.size());
    }

}