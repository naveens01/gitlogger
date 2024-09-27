package com.example.gitintlog.repository;


import com.example.gitintlog.model.PullRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PullRequestRepository extends MongoRepository<PullRequest, String> {
    List<PullRequest> findByAuthor(String author);
}

