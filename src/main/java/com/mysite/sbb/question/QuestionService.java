package com.mysite.sbb.question;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    private QuestionDto of(Question question) {
        return modelMapper.map(question, QuestionDto.class);  // {} Question 객체를 QuestionDto로 변경
    }

    public List<QuestionDto> getList() {
        List<Question> questionList = this.questionRepository.findAll();
        List<QuestionDto> questionDtoList = questionList.stream().map(q -> of(q)).collect(Collectors.toList());
        return questionDtoList;
    }

    public QuestionDto getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return of(question.get());
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
}