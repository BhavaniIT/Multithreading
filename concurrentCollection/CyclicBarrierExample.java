
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierExample {
    private static final int NUM_TOURIST = 5;
    private static final int NUM_STAGES = 3;
    static CyclicBarrier barrier = new CyclicBarrier(NUM_TOURIST, ()->{
        System.out.println("Guide is Instructing");
    });

    static class Tourist implements Runnable {

        private final int touristId;

        public Tourist(int id){
            this.touristId = id;
        }

        @Override
        public void run(){
            for(int i=0; i<NUM_STAGES; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Tourist arrived - "+touristId+"and Stage:"+(i+1));
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<NUM_TOURIST; ++i){
           Thread touristThread = new Thread(new Tourist(i));
           touristThread.start();
        }
    }
    
}
