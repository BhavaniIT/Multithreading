import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class BlockingQueueDemo {
    static int MAX_SIZE = 10;
    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_SIZE);
    public static void main(String[] args){
        Thread producer = new Thread(()->{
            for (int i = 1; i <= 20; i++) {
                System.out.println("Going to produce task is " + i);
                try{
                    queue.put(i);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                System.out.println("Produced task is " + i);
            }
        });

        Thread consumerOne = new Thread(()->{
                while(true) {
                try{
                    int i = queue.take();
                    System.out.println("Consumed task is " + i + " by Consumer One");
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumerTwo = new Thread(()->{
                while(true) {
                try{
                    int i = queue.take();
                    System.out.println("Consumed task is " + i + " by Consumer Two");
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumerOne.start();
        consumerTwo.start();

    }
}
