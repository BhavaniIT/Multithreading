public class ExtendsThreadExample{
    public static void main(String[] args) {
        Thread one = new ThreadOne();
        Thread two = new ThreadTwo();

        one.start();
        two.start();

    }
}

class ThreadOne extends Thread{
    @Override
    public void run(){
        for(int i=0; i<9; i++) {
            System.out.println("Thread One" + i);
        }
    }
}

class ThreadTwo extends Thread{
    @Override
    public void run(){
        for(int i=0; i<9; i++) {
            System.out.println("Thread Two" + i);
        }
    }
}