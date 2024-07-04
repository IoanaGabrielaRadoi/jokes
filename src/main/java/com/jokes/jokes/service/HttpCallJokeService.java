package com.jokes.jokes.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jokes.jokes.model.Joke;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class HttpCallJokeService {

    private static final String URL = "https://official-joke-api.appspot.com/random_joke";

    public Joke getAJoke() throws IOException {
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseStream, Joke.class);
    }
}
