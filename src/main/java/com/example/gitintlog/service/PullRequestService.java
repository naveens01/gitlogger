package com.example.gitintlog.service;

import com.example.gitintlog.model.PullRequest;
import com.example.gitintlog.repository.PullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PullRequestService {

    @Autowired
    private PullRequestRepository pullRequestRepository;

    // Retrieve pull requests by author
    public List<PullRequest> getPRsByAuthor(String author) {
        return pullRequestRepository.findByAuthor(author);
    }

    // Save a new pull request
    public PullRequest savePR(PullRequest pullRequest) {
        if (pullRequest.getTitle() == null || pullRequest.getTitle().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pull request title cannot be empty");
        }
        return pullRequestRepository.save(pullRequest);
    }
    // Retrieve all pull requests
    public List<PullRequest> getAllPRs() {
        return pullRequestRepository.findAll();
    }

    // Retrieve a pull request by ID
    public PullRequest getPRById(String id) {
        return pullRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pull request not found"));
    }

    // Update a pull request
    public PullRequest updatePR(String id, PullRequest updatedPullRequest) {
        PullRequest existingPullRequest = getPRById(id); // Reuse the method to find by ID
        // Update fields as needed, e.g., title, status, etc.
        existingPullRequest.setTitle(updatedPullRequest.getTitle());
        existingPullRequest.setStatus(updatedPullRequest.getStatus());
        return pullRequestRepository.save(existingPullRequest);
    }

    // Delete a pull request
    public void deletePR(String id) {
        if (!pullRequestRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pull request not found");
        }
        pullRequestRepository.deleteById(id);
    }
}
