package filter;

import model.Answer;
import model.Status;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class AnswerDecoder implements Decoder.Text<Answer> {
    @Override
    public Answer decode(String jsonMessage) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
        Answer answer = new Answer();
        answer.setId(Long.valueOf(jsonObject.getString("id")));
        answer.setURL(jsonObject.getString("URL"));
        answer.setResponseTime(Status.valueOf(jsonObject.getString("responseTime")));
        answer.setResponseCode(Status.valueOf(jsonObject.getString("responseCode")));
        answer.setResponseLength(Status.valueOf(jsonObject.getString("responseLength")));
        answer.setSubstringEntry(Status.valueOf(jsonObject.getString("substringEntry")));
        answer.setCooldown(Integer.valueOf(jsonObject.getString("cooldown")));
        answer.setResponseTimeOK(Integer.valueOf(jsonObject.getString("responseTimeOK")));
        answer.setResponseTimeWARNING(Integer.valueOf(jsonObject.getString("responseTimeWARNING")));
        answer.setExpectedResponseCode(Integer.valueOf(jsonObject.getString("expectedResponseCode")));
        answer.setMinResponseLength(Integer.valueOf(jsonObject.getString("minResponseLength")));
        answer.setMaxResponseLength(Integer.valueOf(jsonObject.getString("maxResponseLength")));
        answer.setSubstring(jsonObject.getString("substring"));
        return answer;
    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON
            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
