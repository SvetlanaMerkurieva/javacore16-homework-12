package src.task2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class NumberThread extends Thread {
    private int number;
    private final Consumer<Integer> processor;
    private final AtomicBoolean processed = new AtomicBoolean(true);

    public NumberThread (Consumer<Integer> processor) {
        this.processor = processor;
    }

    public boolean processed() {
        return processed.get();
    }

    public void process (int number) {
        this.number = number;
        this.processed.set(false);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (processed.get()) {
                continue;
            }

            processor.accept(number);
            processed.set(true);
        }
    }
}
