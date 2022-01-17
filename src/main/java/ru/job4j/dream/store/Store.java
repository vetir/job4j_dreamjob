package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private static final Store INST = new Store();

    private Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private static AtomicInteger POST_ID = new AtomicInteger(4);

    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);

    private Store() {
        posts.put(1, new Post(1, "Vasya", "Junior Java Job", LocalDateTime.now()));
        posts.put(2, new Post(2, "Tolya", "Middle Java Job", LocalDateTime.now()));
        posts.put(3, new Post(3, "Kolya", "Senior Java Job", LocalDateTime.now()));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.getAndIncrement());
        }
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public Candidate findByIdCandidate(int id) {
        return candidates.get(id);
    }

    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
        candidate.setId(CANDIDATE_ID.getAndIncrement());
        }
        candidates.put(candidate.getId(), candidate);
    }
}
