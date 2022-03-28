package com.example.models.common;

import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
public class ExtTaskListDto extends CompletableFuture<ExtTaskListDto> {

    private List<ExtTaskDto> tasks;

}
