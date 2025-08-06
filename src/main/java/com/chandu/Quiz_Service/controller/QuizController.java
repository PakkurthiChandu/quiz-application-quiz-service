package com.chandu.Quiz_Service.controller;

import com.chandu.Quiz_Service.dto.QuestionDTO;
import com.chandu.Quiz_Service.dto.QuizDTO;
import com.chandu.Quiz_Service.model.Quiz;
import com.chandu.Quiz_Service.model.Response;
import com.chandu.Quiz_Service.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNumQ(), quizDTO.getTitle());
    }

    @PostMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuiz(@PathVariable Long id) {
     return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Long id,
                                              @RequestBody List<Response> reponses) {
        return quizService.submitQuiz(id, reponses);
    }

    @GetMapping("/getAllQuizs")
    public ResponseEntity<List<Quiz>> getAllQuizs() {
        return new ResponseEntity<>(quizService.findAll(),  HttpStatus.OK);
    }
}