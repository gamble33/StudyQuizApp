package utils;

import interfaces.AnswerCallback;
import interfaces.GeneralCallback;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class TimeUtils {

    public static void setTimeout(GeneralCallback callback, int delay) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                callback.run();
            }
        });
        new Thread(sleeper).start();
    }

}
