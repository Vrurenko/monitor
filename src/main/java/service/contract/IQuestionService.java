package service.contract;

import model.Question;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IQuestionService {

    public List<Question> getQuestionsList();

    public void updateProperties(Question question) throws IOException;

}
