package zjut;


import zjut.replacementAlgorithm.FIFO;
import zjut.replacementAlgorithm.LRU;
import zjut.replacementAlgorithm.OPT;

import java.util.LinkedList;


public class Process {
    public static final int STARTFRAMENUMBER = 0;
    public static final int ENDFRAMENUMBER = 20;
    public static final int PAGENUMBER = 100;
    public static final int MAXFREEFRAME = 5; //最大空余帧

    private LinkedList<Integer> frameQueue; //帧队列
    private int frameNum; //占用帧数
    private FIFO FIFOOPERATOR; //FIFO算法的操作器，用来操作帧队列
    private OPT OPTOPERATOR; //OPT算法的操作器，用来操作帧队列
    private LRU LRUOPERATOR;
    private int count;
    public Process() {
        frameQueue = new LinkedList<>();
        frameNum = 0;
        count = 0;
        FIFOOPERATOR = new FIFO(new Page(PAGENUMBER).getPageList(STARTFRAMENUMBER, ENDFRAMENUMBER), frameNum);
        OPTOPERATOR = new OPT(new Page(PAGENUMBER).getPageList(STARTFRAMENUMBER, ENDFRAMENUMBER), frameNum);
        LRUOPERATOR = new LRU(new Page(PAGENUMBER).getPageList(STARTFRAMENUMBER, ENDFRAMENUMBER), frameNum);
    }


    /**
     * 执行FIFO算法
     */
    public void letFIFODo() {
        if (frameNum < MAXFREEFRAME) { //未满
            frameQueue = FIFOOPERATOR.addFrame(this.frameQueue, count);
            frameNum = FIFOOPERATOR.getLen();
        } else { //已满
            this.frameQueue = FIFOOPERATOR.replace(this.frameQueue, count);
        }
        count++;
    }

    /**
     * 执行OPT算法
     */
    public void letOPTDo() {
        if (frameNum < MAXFREEFRAME) {
            frameQueue = OPTOPERATOR.addFrame(this.frameQueue, count);
            frameNum = OPTOPERATOR.getLen();
        } else {
            this.frameQueue = OPTOPERATOR.replace(this.frameQueue, count);
        }
        count++;
    }

    /**
     * 执行LRU算法
     */
    public void letLRUDo() {
        if (frameNum < MAXFREEFRAME) {
            frameQueue = LRUOPERATOR.addFrame(this.frameQueue, count);
            frameNum = LRUOPERATOR.getLen();
        } else {
            this.frameQueue = LRUOPERATOR.replace(this.frameQueue, count);
        }
        count++;
    }

    /**
     * 打印随机页号序列
     */
    public void printRandomListOPT() {
        OPTOPERATOR.print();
    }

    public void printRandomListFIFO() {
        FIFOOPERATOR.print();
    }

    public void printRandomListLRU() {
        LRUOPERATOR.print();
    }


    /**
     * 输出帧链表函数
     */
    private void printLinkedList() {
        System.out.print("   [");
        for (Integer i : frameQueue) {
            System.out.print(" " + i + " ");
        }
        System.out.print("]  ");

    }

    public void printLinkedListFIFO() {
        printLinkedList();
        System.out.println("页面置换的次数为: " + FIFOOPERATOR.getTimes());
    }

    public void printLinkedListOPT() {
        printLinkedList();
        System.out.println("页面置换的次数为: " + OPTOPERATOR.getTimes());
    }

    public void printLinkedListLRU() {
        printLinkedList();
        System.out.println("页面置换的次数为: " + LRUOPERATOR.getTimes());
    }

    /**
     * 输出缺页率
     */
    public double printRateFIFO() {
        double rate = FIFOOPERATOR.getTimes() *1.0 / PAGENUMBER;
        System.out.println("缺页率为: " + rate);
        return rate;
    }
    public double printRateOPT() {
        double rate = OPTOPERATOR.getTimes() * 1.0 / PAGENUMBER;
        System.out.println("缺页率为: " + rate);
        return rate;
    }
    public double printRateLRU() {
        double rate = LRUOPERATOR.getTimes() * 1.0 / PAGENUMBER;
        System.out.println("缺页率为: " + rate);
        return rate;
    }
}
