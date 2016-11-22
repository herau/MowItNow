package com.github.herau.dto;

import com.github.herau.domain.Cardinal;
import lombok.Value;

import java.util.List;

@Value
public class MowerSteps {

    List<Step> steps;

    @Value
    public static class Step {

        int x;

        int y;

        Cardinal direction;
    }
}
