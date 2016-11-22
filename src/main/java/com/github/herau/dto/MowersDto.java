package com.github.herau.dto;

import com.github.herau.domain.Lawn;
import lombok.Value;

import java.util.List;

@Value
public class MowersDto {

    Lawn lawn;

    List<MowerSteps> mowers;

}
