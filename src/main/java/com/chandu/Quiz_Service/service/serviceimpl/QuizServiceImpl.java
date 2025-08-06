package com.chandu.Quiz_Service.service.serviceimpl;

import com.chandu.Quiz_Service.dto.QuestionDTO;
import com.chandu.Quiz_Service.dto.QuizDTO;
import com.chandu.Quiz_Service.feign.QuizInterface;
import com.chandu.Quiz_Service.model.Quiz;
import com.chandu.Quiz_Service.model.Response;
import com.chandu.Quiz_Service.repository.QuizRepository;
import com.chandu.Quiz_Service.service.QuizService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

//    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    private final QuizInterface  quizInterface;

    public QuizServiceImpl(QuizRepository quizRepository, QuizInterface quizInterface) {
        this.quizRepository = quizRepository;
        this.quizInterface = quizInterface;
    }

    @Override
    public ResponseEntity<String> createQuiz(String category, int numOfQuestions, String title) {

        List<Long> questionIds = quizInterface.generateQuestion(category, numOfQuestions).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> getQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        if (quiz != null) {
            List<QuestionDTO>  questionDTOList =
                    quizInterface.getAllQuestionsByCategory(quiz.getQuestionIds()).getBody();

            return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Integer> submitQuiz(Long id, List<Response> reponses) {
        return quizInterface.getScore(reponses);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }
}