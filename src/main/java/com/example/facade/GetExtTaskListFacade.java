package com.example.facade;

import com.example.models.common.ExtTaskDto;
import com.example.models.common.ExtTaskListDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GetExtTaskListFacade {

    private RestTemplate restTemplate = new RestTemplate();

    public List<ExtTaskDto> getExtTaskList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "admin");

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<ExtTaskListDto> response = restTemplate.exchange("http://localhost:8080/task?all=false", HttpMethod.GET,
                    request, ExtTaskListDto.class);
            return response.getBody().getTasks();
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ExtTaskDto> getAllExtTaskList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "admin");

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<ExtTaskListDto> response = restTemplate.exchange("http://localhost:8080/task?all=true", HttpMethod.GET,
                    request, ExtTaskListDto.class);
            return response.getBody().getTasks();
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteExtTask(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "admin");

        HttpEntity<String> request = new HttpEntity<>(headers);

        restTemplate.exchange("http://localhost:8080/task/" + id, HttpMethod.DELETE, request, String.class);
    }
}
