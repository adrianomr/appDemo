package com.demo.contact.web.resource;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MovieRequest {

    @Min(1)
    @Max(5)
    Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
