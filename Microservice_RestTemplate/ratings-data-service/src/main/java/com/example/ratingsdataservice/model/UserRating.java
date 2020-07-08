package com.example.ratingsdataservice.model;

import java.util.Arrays;
import java.util.List;

public class UserRating {

    // For External API call
    private String userId;
    private List<Rating> ratings;

    // Hard-COde
   // private List<Rating> userRatingList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    // Hard-code
  /*  public List<Rating> getUserRatingList() {
        return userRatingList;
    }

    public void setUserRatingList(List<Rating> userRatingList) {
        this.userRatingList = userRatingList;
    }*/

    // for External APi call
    public void initData(String userId) {
        this.setUserId(userId);
        this.setRatings(Arrays.asList(
                new Rating("100",3),
                new Rating("200",4)
        ));
    }
}
