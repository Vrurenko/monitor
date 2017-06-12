package service;


import service.contract.IAnswerService;
import service.contract.IQuestionService;
import service.contract.ISchedulerService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SchedulerService implements ISchedulerService {
    public static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(15);
    public static LinkedList<ScheduledFuture<?>> scheduledFuture = new LinkedList<>();

    private IQuestionService questionService;
    private IAnswerService answerService;

    @Inject
    public SchedulerService(IQuestionService questionService, IAnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public void setAvailableTasks() {
        questionService
                .getQuestionsList()
                .stream()
                .forEach(question -> scheduledFuture.add(executor
                        .scheduleAtFixedRate(new SendTask(question, answerService), 0, question.getCooldown(), TimeUnit.SECONDS)));
    }

    public void reScheduleTasks() {
        scheduledFuture.stream().forEach(future -> future.cancel(true));
        scheduledFuture = new LinkedList<>();
        setAvailableTasks();
    }
}
