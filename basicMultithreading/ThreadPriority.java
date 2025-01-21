public class ThreadPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); 
        System.out.println(Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println(Thread.currentThread().getPriority());

        /*Second example */
        System.out.println("Second example");
        System.out.println(Thread.currentThread().getName() +"says Hi!");
        Thread one = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "says Hi!");
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();
    }
}
