
public class JoinThreadExample {
    /*
     * main method called by JVM first
     * main thread highest priority
     * 1. create definition for thread1
     * 2. create definition for thread2
     * 3. Done execution printed because main thread has highest priority
     * 4. Other threads started 
     * 5. "mian" thread waits for other 2 threds to finish
     * 6. The program terminates after that
     */
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(()->{
            for(int i = 0; i < 10; ++i){
                System.out.println("Thread 1:" + i);
            }
        });
    
        Thread two = new Thread(()->{
            for(int i = 0; i < 25; ++i){
                System.out.println("Thread 2:" + i);
            }
        });

        one.start();
        two.start();
        /*
         * Join - 1st thread will be executed
         * JVM will wait for thread 1 to finish
         * If 2 threads calls join
         * JVM will wait for both threads to finish
         */
        one.join();
        System.out.println("Done Executing Threads");
    }
        
}


