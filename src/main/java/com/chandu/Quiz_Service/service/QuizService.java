package com.chandu.Quiz_Service.service;

import com.chandu.Quiz_Service.dto.QuestionDTO;
import com.chandu.Quiz_Service.dto.QuizDTO;
import com.chandu.Quiz_Service.model.Quiz;
import com.chandu.Quiz_Service.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    ResponseEntity<String> createQuiz(String category, int numQ, String title);

    ResponseEntity<List<QuestionDTO>> getQuiz(Long id);
//
    ResponseEntity<Integer> submitQuiz(Long id, List<Response> reponses);

    List<Quiz> findAll();
}
