package com.jokes.jokes.controller;


import com.jokes.jokes.model.Joke;
import com.jokes.jokes.service.AggregatorPageJokes;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/jokes-service")
public class JokeController {

    private AggregatorPageJokes aggregatorPageJokes;

    public JokeController(AggregatorPageJokes aggregatorPageJokes) {
        this.aggregatorPageJokes = aggregatorPageJokes;
    }

    @GetMapping("/generate")
    public String generateNrOfJokes(@RequestParam(value = "count") int count) throws IOException, InterruptedException {
        if (count > 100) {
            throw new IllegalArgumentException("You cannot request more than 100 jokes at a time!");
        }
        aggregatorPageJokes.generate(count);

        return "Generated started!";
    }

    @GetMapping("/getByPage")
    public List<Joke> getJokesFromDbByPage(@RequestParam(value = "pageNo") int pageNo) throws IOException {
        return aggregatorPageJokes.getByPage(PageRequest.of(pageNo, 10));
    }

    @GetMapping("/reset")
    public String getJokesFromDbByPage() {
        aggregatorPageJokes.delete();
        return "Reset started!";
    }
}
