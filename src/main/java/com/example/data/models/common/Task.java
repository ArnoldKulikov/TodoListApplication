package com.example.data.models.common;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity(name = "tasks")
@Accessors(chain = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean closed;

    @Column(nullable = false)
    private String description;

/*    @OneToOne
    private User user;*/

    public Task taskServices(String description) {
        return new Task()
                .setClosed(false)
                .setDescription(description);
    }
}