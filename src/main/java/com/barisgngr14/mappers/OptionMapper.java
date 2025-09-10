package com.barisgngr14.mappers;

import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.Question;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OptionMapper {

    public static List<Option> toOptionEntity(List<Option> options, Question question){
        return options.stream()
                .map(o ->{
                        Option newOption = new Option();
                        newOption.setOptionId(String.valueOf(UUID.randomUUID()));
                        newOption.setIsCorrect(o.getIsCorrect());
                        newOption.setOptionText(o.getOptionText());
                        newOption.setQuestion(question);
                        return newOption;
                })
                .collect(Collectors.toList());

    }
}
