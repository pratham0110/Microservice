package com.example.moviecatalogservice.resource;

import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.Movie;
import com.example.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //RestTemplate restTemplate=new RestTemplate();   //without @Bean and @Autowired

        UserRating ratings=restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);

        return ratings.getRatings().stream().map(rating -> {

            //for each MovieID, call movie-info-service and get Details.
            Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);

            //put them all together
            return new CatalogItem(movie.getMovie_name(),movie.getDescription(),rating.getRatings());
        })
        .collect(Collectors.toList());
    }

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem("No movie","",0));
    }
}
