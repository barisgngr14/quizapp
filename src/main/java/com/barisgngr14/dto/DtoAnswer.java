package com.barisgngr14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAnswer {
    private Map<String, DtoOption> answers;
    private String solveDataId;
    private Integer solveTime;
    private Integer timeLeft;
}
