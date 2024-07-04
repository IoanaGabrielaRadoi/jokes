package com.jokes.jokes.service;

import com.jokes.jokes.model.Joke;
import com.jokes.jokes.repository.JokeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JokeService {

    private JokeRepository repository;

    public JokeService(JokeRepository repository) {
        this.repository = repository;
    }
    public void save(Joke joke) {
        repository.save(joke);
    }

    public Page<Joke> getByPage(Pageable paging) {
        return repository.findAll(paging);
    }

    public void delete() {
        repository.deleteAll();
    }
}
