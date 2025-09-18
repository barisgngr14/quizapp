package com.barisgngr14.dto;

import com.barisgngr14.entities.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoOption {
    private Option selectedOption;
}
