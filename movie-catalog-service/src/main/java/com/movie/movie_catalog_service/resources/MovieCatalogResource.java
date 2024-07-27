package com.movie.movie_catalog_service.resources;

import com.movie.movie_catalog_service.models.CatalogItem;
import com.movie.movie_catalog_service.models.MovieInfo;
import com.movie.movie_catalog_service.models.RatingsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        List<RatingsData> ratings = Arrays.asList(
                new RatingsData("123", 4),
                new RatingsData("456", 5)
        );

        return ratings.stream().map(rating -> {
            MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), MovieInfo.class);
            return new CatalogItem(movieInfo.getMovieName(), "Desc", rating.getRating());
        }).collect(Collectors.toList());
    }
}
