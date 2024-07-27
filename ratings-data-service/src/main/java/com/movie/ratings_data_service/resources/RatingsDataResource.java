package com.movie.ratings_data_service.resources;

import com.movie.ratings_data_service.models.RatingsData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsData")

public class RatingsDataResource {
    @RequestMapping("/{movieId}")
    public RatingsData getRatingsData(@PathVariable("movieId") String movieId) {
        return new RatingsData(movieId, 4);
    }
}
