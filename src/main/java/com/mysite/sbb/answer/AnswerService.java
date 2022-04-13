package com.mysite.sbb.answer;

import com.mysite.sbb.question.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    private Answer of(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);
    }

    public AnswerDto create(QuestionDto questionDto, String content) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setContent(content);
        answerDto.setCreateDate(LocalDateTime.now());
        answerDto.setQuestion(questionDto);
        Answer answer = of(answerDto);
        this.answerRepository.save(answer);
        return answerDto;
    }
}