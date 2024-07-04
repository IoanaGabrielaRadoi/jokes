package com.jokes.jokes.model;

import org.springframework.data.annotation.Id;


public class Joke {

    @Id
    public Integer id;

    public String setup;
    public String type;
    public String punchline;

    public Joke() {
    }

    public Joke(Integer id, String setup, String type, String punchline) {
        this.id = id;
        this.setup = setup;
        this.type = type;
        this.punchline = punchline;
    }

    @Override
    public String toString() {
        return String.format("Joke[id=%s, setup='%s', type='%s, punchline='%s']", id, id, setup, type, punchline);
    }
}