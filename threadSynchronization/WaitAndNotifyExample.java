public class WaitAndNotifyExample {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread one = new Thread(()->{
            try {
               one(); 
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });
        Thread two = new Thread(()->{
            try {
                two(); 
             } catch (InterruptedException e) {
                 throw new RuntimeException();
             }
        });

        one.start();
        two.start();

    }

    private static void one() throws InterruptedException{
        synchronized(LOCK){
            System.out.println("From 1st thread before wait");
            LOCK.wait();
            System.out.println("From 1st thread after wait");
        }
        
    }

    private static void two() throws InterruptedException{
        synchronized (LOCK) {
            System.out.println("Thread 2 says hi");
            LOCK.notify();
            System.out.println("Thread 2 says Bye");
        }
    }
    
}
