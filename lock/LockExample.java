
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockExample {
    private final int queueSize = 10;
    private final  Lock lock = new ReentrantLock();
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    public static void main(String[] args){
        LockExample le = new LockExample();
        Thread producer = new Thread(()->{
            for(int i=0; i<50; ++i){
                try {
                    le.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread consumer = new Thread(()->{
            try {
                for(int i=0; i<25; ++i){
                    le.consume();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();

    }

    private void produce(int item) throws InterruptedException{
            lock.lock();
            try {
                while(buffer.size() == queueSize){
                    bufferNotFull.await();
                }
                Thread.sleep(1000);
                buffer.offer(item);
                System.out.println("Produced item >>"+item);
                bufferNotEmpty.signal();
            } finally {
                lock.unlock();
            }
    }

    private void consume() throws InterruptedException {
        lock.lock();
        try {
            while(buffer.isEmpty()){
                bufferNotEmpty.await();
            }
            System.out.println("Consumed << " + buffer.poll());
            bufferNotFull.signal();
        } finally {
            lock.unlock();
        }
    }
}

