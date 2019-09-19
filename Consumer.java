class Consumer extends Main {
    private Queue q;
    Consumer(Queue q){
        this.q = q;
    }

    public void run() {
        int i = 0;
        while(i<itemLimit){
            q.get();
            i++;
        }
    }
} 