package com.jokes.jokes.service;

import com.jokes.jokes.model.Joke;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class AggregatorPageJokes {

    private static final int NR_CHUNKS = 5;

    private HttpCallJokeService httpCallJokeService;
    private JokeService jokeService;

    public AggregatorPageJokes(HttpCallJokeService httpCallJokeService, JokeService jokeService) {
        this.httpCallJokeService = httpCallJokeService;
        this.jokeService = jokeService;
    }

    public List<Joke> getByPage(PageRequest pageRequest) throws IOException {
        return jokeService.getByPage(pageRequest).getContent();
    }

    @Async
    public void delete() {
        jokeService.delete();
    }

    @Async
    public void generate(int count) throws InterruptedException {
        int rounds = count / NR_CHUNKS;
        int restJokes = count % NR_CHUNKS;

        for (int i = 0; i < rounds; i++) {
            generateJokes(NR_CHUNKS);
        }
        generateJokes(restJokes);
    }

    private void generateJokes(int chunk) throws InterruptedException {
        IntStream.range(1, chunk).parallel().forEach(nr -> {
            Joke joke = null;
            try {
                joke = httpCallJokeService.getAJoke();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            jokeService.save(joke);
        });
        //"simulate" a ratelimiter
        Thread.sleep(2000);
    }
}
