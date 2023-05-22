import java.util.LinkedList;


public class ReadyQueue {
    private int length;
    int currentTime;
    LinkedList<PCB> readyQueue; //依赖接口Queue模拟就绪队列
    /**
     * 构造函数，初始为空
     */
    public ReadyQueue() {
        readyQueue = new LinkedList<>();
        length = 0;
        currentTime = 1;
    }

    /**
     * 进程加入就绪队列
     *
     * @param process 新建的进程
     */
    public void insertProcess(PCB process) {
        length++;
        readyQueue.addLast(process);
    }

    /**
     * 得到优先级最高的进程
     *如果优先级一样，运行当前在运行的进程
     * @return
     */
    public PCB getLowLaxityProcess() {
        int lowIndex = 0;
        for (int i = 1; i < length; i++) {
            if (readyQueue.get(i).getLaxity() < readyQueue.get(lowIndex).getLaxity())
                lowIndex = i;
        }
        return readyQueue.get(lowIndex);
    }

    /**
     * 中断机制，查看松弛度为0的进程
     * @return 0 laxity的进程
     */
    public PCB get0LaxityProcess() {
        for (int i = 0; i < length; i++) {
            if (readyQueue.get(i).getLaxity() == 0)
                return readyQueue.get(i);
        }
        return null;
    }

    /**
     * 更新所有进程的松弛度
     */
    public void updateAllLaxity() {
        for (int i = 0; i < 3; i++) {
            readyQueue.get(i).updateLaxity(currentTime);
        }
    }




    /**
     * 开始运行
     */
    public void run() {

        PCB low = getLowLaxityProcess();
        boolean flag =  low.running(currentTime);
        updateAllLaxity();
        if (flag) {
            printInformation();
            currentTime++;
        } else {
            currentTime--;
            printInformation();
        }
    }

    /**
     * 输出每个进程的信息
     */
    public void printInformation() {
        String res = "任务开始   " + "此时为" + currentTime + "\n";
        for (int i = 0; i < 3; i++) {
            res += "任务名：" + readyQueue.get(i).getPID() +
                    "   所处周期数：" + readyQueue.get(i).getCycle() +
                    "   调度时刻：" + currentTime +
                    "   当前周期运行持续时间：" + readyQueue.get(i).getRunningTime() +
                    "   松弛度为：" + readyQueue.get(i).getLaxity() +
                    "   进程状态为：" + readyQueue.get(i).getProcessState() + "\n";
        }
        System.out.println(res);
    }
}
