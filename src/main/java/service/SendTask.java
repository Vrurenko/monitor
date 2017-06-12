package service;

import model.Question;
import service.contract.IAnswerService;
import servlet.EchoEndPoint;

import javax.websocket.Session;

public class SendTask implements Runnable {

    private Question question;
    private IAnswerService answerService;


    public SendTask(Question question, IAnswerService answerService) {
        this.question = question;
        this.answerService = answerService;
    }

    @Override
    public void run() {
        EchoEndPoint.sessions.stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getBasicRemote().sendObject(answerService.getAnswer(question));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
