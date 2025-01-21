public class SequentialExample{
    /*Single thread 
     * Created by JVM - thread Name ("main")
     * Also called parent thread
     */
    private static void demo1(){
        for(int i=0; i<5; ++i){
            System.out.println("Demo 1: " + i);
        }
    }

    private static void demo2(){
        for(int i=0; i<5; ++i){
            System.out.println("Demo 2: " + i);
        }
    }
    public static void main(String[] args) {
        demo1();
        demo2();
    }
}