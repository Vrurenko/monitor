package service.contract;

import model.Answer;
import model.Question;

public interface IAnswerService {

    Answer getAnswer(Question question) throws Exception;

}
