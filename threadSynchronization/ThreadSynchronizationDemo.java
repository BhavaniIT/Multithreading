public class ThreadSynchronizationDemo {
    private static int counter = 0;

    /* Reason for wrong output
     * -> Non atomic operation
     * 1. Load
     * 2. Increment
     * 3. Set 
     */
    public static void main(String[] args) {
        Thread one = new Thread(()->{
            for(int i = 0; i < 100000; ++i) counter++;
        });
        Thread two = new Thread(()->{
            for(int i = 0; i < 100000; ++i) counter++;
        });
        
        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        System.out.println(counter);
    }
}
