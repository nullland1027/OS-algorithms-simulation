/**
 * PCB 类，进程控制块，表示一个进程
 */
public class PCB {
    private final String pID; //进程id
    private int laxity; // 进程的优先级
    private String processState; // 表示进程此时的状态
    private final int lastTime; //进程的一次执行时间
    private final int deadTime; //进程的周期时间
    private int runningTime; //当前周期已经执行时间
    private int cycle; //此时的周期数
    private int leftTime; //当前周期剩余时间

    /**
     * 构造函数
     * @param pID 进程id，OS决定
     * @param lastTime 进程的执行时间
     * @param deadTime 进程的周期时间
     */
    public PCB(String pID, int lastTime, int deadTime) {
        this.pID = pID;
        this.laxity = deadTime - lastTime; //初始状态的松弛度
        this.lastTime = lastTime;
        this.deadTime = deadTime;
        processState = "ready"; //刚创建的进程的状态为ready
        runningTime = 0;
        cycle = 1;
        leftTime = lastTime;
    }

    /**
     * 进程以最小颗粒度的时间被执行
     */
    public boolean running(int currentTime) {
        String res = "任务名：" + pID+
                "   所处周期数：" + cycle +
                "   调度时刻：" + currentTime +
                "   当前周期运行持续时间：" + runningTime +
                "   松弛度为：" + laxity +
                "   进程状态为：" + processState + "\n";

        if (runningTime < lastTime && leftTime > 0) {
            setProcessState("running");
            runningTime++;
            leftTime--;
            //System.out.println(res);
            return true;
        } else {
            stop();
            cycle++;
            runningTime = 0;
            leftTime = lastTime;
            return false;
        }
    }

    public void stop() {
        setProcessState("ready");
    }


    // GETTERS
    public String getPID() {
        return pID;
    }

    public int getLaxity() {
        return laxity;
    }

    public String getProcessState() {
        return processState;
    }

    public int getCycle() {
        return cycle;
    }

    public int getRunningTime() {
        return runningTime;
    }

    //SETTERS
    public void updateLaxity(int currentTime) {
        laxity =  cycle * deadTime - currentTime - leftTime;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

}
