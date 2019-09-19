class Producer extends Main {
    private Queue q;
    private Message m;
    Producer(Queue q) {
        this.q = q;
    }

    public void run() {
        int i = 0;
        while(i<itemLimit){
            m = new Message(i);
            q.put(m);
            i++;
        }
    }
}