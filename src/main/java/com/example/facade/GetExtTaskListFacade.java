package com.example.facade;

import com.example.exeption.MyException;
import com.example.models.common.ExtTaskDto;
import com.example.models.common.ExtTaskListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GetExtTaskListFacade {

    @Value("${external.todoListApplication.userName}")
    private String userName;
    @Value("${external.todoListApplication.password}")
    private String password;
    @Value("${external.todoListApplication.baseUrl}")
    private String baseUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public List<ExtTaskDto> getExtTaskList(Boolean all) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(userName,password);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<ExtTaskListDto> response = restTemplate.exchange(baseUrl + "/task?all=" + all, HttpMethod.GET,
                    request, ExtTaskListDto.class);
            return response.getBody().getTasks();
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteExtTask(String id) throws MyException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(userName, password);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(baseUrl + "/task/" + id, HttpMethod.DELETE, request, String.class);

        } catch (RuntimeException e) {
            throw new MyException("taskNotFound");
        }
    }
}
