public class Main extends Thread {
    public static final int itemLimit = 100;
    public static void main(String args[]){
        Queue q = new Queue();
        Thread c = new Consumer(q);
        Thread p = new Producer(q);

        c.start();
        p.start();
    }
}
