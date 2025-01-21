public class ThreadSynchronizationDemo {
    private static int counter1 = 0;
    private static int counter2 = 0;

    /* Reason for wrong output
     * -> Non atomic operation
     * 1. Load
     * 2. Increment
     * 3. Set 
     * Called as race condition
     * 2 threads - same resource
     */

     private static synchronized void increment1(){
        counter1++;
     }

     private static synchronized void increment2(){
        counter2++;
     }

     /* Output will be correct
      * But,
      * Even though t2 has nothing to do with t1
      * method level synchronization will lock the class
      * so T2 will have to wait
      * Issue solved using : Lock with custom object
      */
    
    public static void main(String[] args) {
        Thread one = new Thread(()->{
            for(int i = 0; i < 100000; ++i) increment1();
        });
        Thread two = new Thread(()->{
            for(int i = 0; i < 100000; ++i) increment2();
        });
        
        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        System.out.println(counter1+ "**"+counter2);
    }
}
