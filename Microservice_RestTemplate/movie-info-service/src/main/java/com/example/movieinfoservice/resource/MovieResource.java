package com.example.movieinfoservice.resource;

import com.example.movieinfoservice.model.Movie;
import com.example.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        // for Hard-Code
        return new Movie(movieId, "Test name");


        // for External API
        /*MovieSummary movieSummary=restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+
                    "?api_Key="+apiKey,
                    MovieSummary.class
        );
        return new Movie(movieId, movieSummary.getTitle(),movieSummary.getOverview());*/
    }
}
