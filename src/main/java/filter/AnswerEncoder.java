package filter;

import model.Answer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class AnswerEncoder implements Encoder.Text<Answer>  {
    @Override
    public String encode(Answer answer) throws EncodeException {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", answer.getId())
                .add("URL", answer.getURL())
                .add("responseTime", answer.getResponseTime().toString())
                .add("responseCode", answer.getResponseCode().toString())
                .add("responseLength", answer.getResponseLength().toString())
                .add("substringEntry", answer.getSubstringEntry().toString())
                .add("cooldown", answer.getCooldown())
                .add("responseTimeOK", answer.getResponseTimeOK())
                .add("responseTimeWARNING", answer.getResponseTimeWARNING())
                .add("expectedResponseCode", answer.getExpectedResponseCode())
                .add("minResponseLength", answer.getMinResponseLength())
                .add("maxResponseLength", answer.getMaxResponseLength())
                .add("substring", answer.getSubstring())
                .build();
        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
