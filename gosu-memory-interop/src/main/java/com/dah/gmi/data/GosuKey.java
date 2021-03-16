package com.dah.gmi.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class GosuKey {
    @JsonAlias("isPressed")
    private boolean isPressed;
    private int count;
}
