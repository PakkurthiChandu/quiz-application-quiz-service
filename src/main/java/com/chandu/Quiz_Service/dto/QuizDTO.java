package com.chandu.Quiz_Service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDTO {

    private String category;
    private String title;
    private Integer numQ;
}
