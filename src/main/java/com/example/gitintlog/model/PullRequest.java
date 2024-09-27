package com.example.gitintlog.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "pullRequests")
public class PullRequest {
    @Id
    private String id;
    private String title;
    private String author;
    private String repository;
    private String status;  // Merged, Pending, Closed
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
