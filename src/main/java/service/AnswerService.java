package service;

import model.Answer;
import model.Question;
import model.Status;
import service.contract.IAnswerService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AnswerService implements IAnswerService {

    @Override
    public Answer getAnswer(Question question) throws Exception {

        Answer answer = new Answer();

        answer.setId(question.getId());
        answer.setURL(question.getURL());
        answer.setCooldown(question.getCooldown());
        answer.setResponseTimeOK(question.getResponseTimeOK());
        answer.setResponseTimeWARNING(question.getResponseTimeWARNING());
        answer.setExpectedResponseCode(question.getExpectedResponseCode());
        answer.setMinResponseLength(question.getMinResponseLength());
        answer.setMaxResponseLength(question.getMaxResponseLength());
        answer.setSubstring(question.getSubstring());


        //Getting the server response time
        long start = System.currentTimeMillis();

        String inputLine;
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) new URL(question.getURL()).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        long end = System.currentTimeMillis();

        Status status = end - start < question.getResponseTimeOK() ? Status.OK :
                end - start < question.getResponseTimeWARNING() ? Status.WARNING : Status.CRITICAL;
        answer.setResponseTime(status);

        //Getting the HTTP response code
        if (connection.getResponseCode() == question.getExpectedResponseCode()) {
            answer.setResponseCode(Status.OK);
        } else {
            answer.setResponseCode(Status.CRITICAL);
        }

        //Getting the response length if Content-Length header is not missing, otherwise CRITICAL
        status = connection.getContentLength() < question.getMinResponseLength() ||
                connection.getContentLength() > question.getMaxResponseLength() ? Status.CRITICAL : Status.OK;
        answer.setResponseLength(status);

        //Getting the response substring
        answer.setSubstringEntry(response.toString().contains(question.getSubstring()) ? Status.OK : Status.CRITICAL);


        return answer;
    }


}
