package servlet;

import filter.AnswerDecoder;
import filter.AnswerEncoder;
import service.contract.ISchedulerService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

@ServerEndpoint(value = "/echo", encoders = {AnswerEncoder.class}, decoders = {AnswerDecoder.class})
public class EchoEndPoint {

    private static final Logger log = Logger.getLogger(EchoEndPoint.class.getName());
    public static final Set<Session> sessions = new CopyOnWriteArraySet<>();

    @Inject
    private ISchedulerService schedulerService;


    @PostConstruct
    public void postConstruct() {
        this.schedulerService.reScheduleTasks();
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void echo(String message) {
    }

    @OnError
    public void onError(Session session, Throwable error) {
        sessions.remove(session);
    }

}
