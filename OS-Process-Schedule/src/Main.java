import java.util.*;



public class Main {
    private static int time;
    public static void main(String[] args) throws InterruptedException {
        time = 0;
        PCB a = new PCB("A", 5, 20);
        PCB b = new PCB("B", 10, 30);
        PCB c = new PCB("C", 8, 50);
        ReadyQueue queue = new ReadyQueue();
        queue.insertProcess(a);
        queue.insertProcess(b);
        queue.insertProcess(c);
        while (true) {
            try {
                Thread.sleep(200);
                queue.run();
            } catch (Exception e) {
                System.exit(0);
            }

        }

    }
}
