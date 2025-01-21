import java.util.LinkedList;
import java.util.List;

class Worker {
    private final int top; // Maximum capacity
    private final int bottom; // Minimum capacity (usually 0)
    private final Object LOCK = new Object(); // Lock for synchronization
    private final List<Integer> container; // Shared buffer
    private int sequence; // Counter to keep track of items produced

    public Worker(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new LinkedList<>(); // Changed to LinkedList for removeFirst()
        this.sequence = 0;
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (LOCK) {
                // Wait if the container is full
                while (container.size() == top) {
                    System.out.println("Container is full...");
                    LOCK.wait();
                }
                // Produce an item
                System.out.println("Produced: " + sequence);
                container.add(sequence++);
                LOCK.notify(); // Notify the consumer
            }
            Thread.sleep(500); // Sleep outside the synchronized block
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (LOCK) {
                // Wait if the container is empty
                while (container.size() == bottom) {
                    System.out.println("Container is empty...");
                    LOCK.wait();
                }
                // Consume an item
                System.out.println("Consumed: " + container.remove(0));
                LOCK.notify(); // Notify the producer
            }
            Thread.sleep(500); // Sleep outside the synchronized block
        }
    }
}

public class ProduceConsume {
    public static void main(String[] args) {
        Worker worker = new Worker(5, 0); // Buffer size 5

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
