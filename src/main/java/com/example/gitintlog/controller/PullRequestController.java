package com.example.gitintlog.controller;

import com.example.gitintlog.model.PullRequest;
import com.example.gitintlog.service.PullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pull-requests")
public class PullRequestController {

    @Autowired
    private PullRequestService pullRequestService;

    // Endpoint to get all pull requests
    @GetMapping
    public ResponseEntity<List<PullRequest>> getAllPRs() {
        List<PullRequest> pullRequests = pullRequestService.getAllPRs();
        return new ResponseEntity<>(pullRequests, HttpStatus.OK);
    }

    // Endpoint to get pull requests by author
    @GetMapping("/author/{author}")
    public ResponseEntity<List<PullRequest>> getPRsByAuthor(@PathVariable String author) {
        List<PullRequest> pullRequests = pullRequestService.getPRsByAuthor(author);
        return new ResponseEntity<>(pullRequests, HttpStatus.OK);
    }

    // Endpoint to create a new pull request
    @PostMapping
    public ResponseEntity<PullRequest> createPR(@RequestBody PullRequest pullRequest) {
        try {
            PullRequest savedPR = pullRequestService.savePR(pullRequest);
            return new ResponseEntity<>(savedPR, HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(null, ex.getStatusCode()); // Update this line
        }
    }

    // Endpoint to get a pull request by ID
    @GetMapping("/{id}")
    public ResponseEntity<PullRequest> getPRById(@PathVariable String id) {
        try {
            PullRequest pullRequest = pullRequestService.getPRById(id);
            return new ResponseEntity<>(pullRequest, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(null, ex.getStatusCode()); // Update this line
        }
    }

    // Endpoint to update a pull request
    @PutMapping("/{id}")
    public ResponseEntity<PullRequest> updatePR(@PathVariable String id, @RequestBody PullRequest pullRequest) {
        try {
            PullRequest updatedPR = pullRequestService.updatePR(id, pullRequest);
            return new ResponseEntity<>(updatedPR, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(null, ex.getStatusCode()); // Update this line
        }
    }

    // Endpoint to delete a pull request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePR(@PathVariable String id) {
        try {
            pullRequestService.deletePR(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
