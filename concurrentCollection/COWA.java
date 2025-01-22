
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class COWA {
public static void main(String[] args) {
    Simulator sim = new Simulator();
    sim.simulate();
}
}

class Simulator{
    List<Integer> list;
    public Simulator() {
        this.list = new CopyOnWriteArrayList<>();
        this.list.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
    }

    public void simulate(){
        Thread one = new Thread(new Write(list));
        Thread two = new Thread(new Write(list));
        Thread three = new Thread(new Write(list));
        Thread four = new Thread(new Read(list));
        
        one.start();
        two.start();
        three.start();
        four.start();
    }
}

class Read implements Runnable {
    private List<Integer> list;

    public Read(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println("Reading the list ..."+list);
        }
       
    }

}

class Write implements Runnable {
    List<Integer> list;
    Random random;

    public Write(List<Integer> list){
        this.list = list;
        this.random = new Random();
    }
    
    @Override
    public void run(){

        while(true){
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            list.set(random.nextInt(list.size()),random.nextInt(10));
        }
        
    }

}
