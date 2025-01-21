package multithreading.basicMultithreading;

public class RunnableThreadExample {
    public static void main(String[] args) {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        Thread three = new Thread(()->{
            for(int i=0; i<5; ++i){
                System.out.println("Demo 1: " + i);
            }    
        });
        
        one.start();
        two.start();
        three.start();
    }
}

class ThreadOne implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<5; ++i){
            System.out.println("Demo 1: " + i);
        }
    }
}

class ThreadTwo implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<5; ++i){
            System.out.println("Demo 2: " + i);
        }
    }
}