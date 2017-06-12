package service;

import filter.PropertiesFilter;
import model.Question;
import service.contract.IQuestionService;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class QuestionService implements IQuestionService {

    private final String FOLDER_PATH = "C:\\Users\\Comandante\\Desktop\\monitor\\target\\classes";

    private List<String> getPathList() {
        File folder = new File(FOLDER_PATH);
        PropertiesFilter filter = new PropertiesFilter();
        return Arrays.asList(folder.listFiles(filter))
                .stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionsList() {
        List<Question> questions = new LinkedList<>();
        for (String path : getPathList()) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(path));
            } catch (IOException e) {
                System.out.println("FILE IS MISSING");
                continue;
            }
            Question question = new Question();
            question.setId(Long.valueOf(properties.getProperty("id")));
            question.setURL(properties.getProperty("url"));
            question.setCooldown(Integer.valueOf(properties.getProperty("cooldown")));
            question.setResponseTimeOK(Integer.valueOf(properties.getProperty("responseTimeOK")));
            question.setResponseTimeWARNING(Integer.valueOf(properties.getProperty("responseTimeWARNING")));
            question.setExpectedResponseCode(Integer.valueOf(properties.getProperty("expectedResponseCode")));
            question.setMinResponseLength(Integer.valueOf(properties.getProperty("minResponseLength")));
            question.setMaxResponseLength(Integer.valueOf(properties.getProperty("maxResponseLength")));
            question.setSubstring(properties.getProperty("substring"));
            questions.add(question);
        }
        return questions;
    }

      public void updateProperties(Question question) throws IOException {
          for (String property: getPathList()) {
              FileInputStream in = new FileInputStream(property);
              Properties props = new Properties();
              props.load(in);
              in.close();
              if (Integer.valueOf(props.getProperty("id")) == question.getId()) {

                  props.setProperty("id", String.valueOf(question.getId()));
                  props.setProperty("url", question.getURL());
                  props.setProperty("cooldown", String.valueOf(question.getCooldown()));
                  props.setProperty("responseTimeOK", String.valueOf(question.getResponseTimeOK()));
                  props.setProperty("responseTimeWARNING", String.valueOf(question.getResponseTimeWARNING()));
                  props.setProperty("expectedResponseCode", String.valueOf(question.getExpectedResponseCode()));
                  props.setProperty("minResponseLength", String.valueOf(question.getMinResponseLength()));
                  props.setProperty("maxResponseLength", String.valueOf(question.getMaxResponseLength()));
                  props.setProperty("substring", question.getSubstring());

                  FileOutputStream out = new FileOutputStream(property);
                  props.store(out, null);
                  out.close();
              }
          }




    }


}
