import java.util.concurrent.Semaphore;

class Queue{
    private static final int bufferSize = 6;
    private volatile Message[] itemArray = new Message[bufferSize];

    private Semaphore semCon = new Semaphore(0);
    private Semaphore semProd = new Semaphore(bufferSize);

    void get() {
        try {
            semCon.acquire();
        }catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        System.out.println("Consumer consumed item#" + (bufferSize-semCon.availablePermits()-1) + " : " + itemArray[bufferSize-semCon.availablePermits()-1].content);
        if(semCon.availablePermits()==0) semProd.release(bufferSize);
    }

    void put(Message item) {
        try {
            semProd.acquire();
        }catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        itemArray[bufferSize-semProd.availablePermits()-1]=item;
        System.out.println("Producer produced item#" + (bufferSize-semProd.availablePermits()-1) + " : " + itemArray[bufferSize-semProd.availablePermits()-1].content);
        if(semProd.availablePermits()==0 || item.content>=Main.itemLimit-1) semCon.release(bufferSize);
    }
}