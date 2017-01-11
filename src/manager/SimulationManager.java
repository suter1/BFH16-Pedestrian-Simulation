package manager;

import events.FinishedEvent;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import model.persons.Person;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tgdflto1 on 26/10/16.
 */
public class SimulationManager {
    private static SimulationManager instance;
    private static SpawnManager spawnManager = SpawnManager.getInstance();
    private static Thread simulation;
    public static LongProperty speedProperty = new SimpleLongProperty();
    private static ArrayList<FinishedEvent> finishedListeners = new ArrayList();

    private SimulationManager() {
    }

    public static SimulationManager getInstance() {
        if (instance == null) instance = new SimulationManager();
        return instance;
    }

    /**
     * shuffle before every run because there might be
     * unsolvable issues if it is always the same order
     */
    public static void start(Label time, int oldTime) {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
            	String t = time.getText();
            	int i = oldTime;
                while (!isSimulationFinished()) {
                    Collections.shuffle(spawnManager.getPersons());
                    spawnManager.handlePersonsInTargetRange();
                    Platform.runLater(() -> {
                        for (Person p : spawnManager.getPersons())
                            p.calculateStep();
                    });
                    this.updateMessage(++i + " s");
                    Thread.sleep(speedProperty.getValue());
                }
                return null;
            }
        };
        time.textProperty().bind(task.messageProperty());
        simulation = new Thread(task);
        simulation.start();
    }

    private static boolean isSimulationFinished() {
        for (Person p : spawnManager.getPersons()) {
            if (!p.isInGoalArea()) return false;
        }
        sendEvents();
        return true;
    }

    private static void sendEvents() {
        for (FinishedEvent fe : finishedListeners)
            fe.finished();
    }

    public Thread getSimulationThread() {
        return simulation;
    }

    public static void addEventListener(FinishedEvent finishedEvent) {
        finishedListeners.add(finishedEvent);
    }
}
