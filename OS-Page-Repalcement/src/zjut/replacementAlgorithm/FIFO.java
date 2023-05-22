package zjut.replacementAlgorithm;


import java.util.Arrays;
import java.util.LinkedList;

public class FIFO {
    private final int[] list;
    private int times; // 置换次数
    private int len;
    /**
     * 得到Page生成的list
     * @param pageList
     */
    public FIFO(int[] pageList, int len) {
        list = pageList;
        times = 0;
        this.len = len;
    }

    /**
     * 帧表未满时添加
     * @param linkedList 进程的帧表
     * @param count 计数器
     * @return 更新后的帧表
     */
    public LinkedList<Integer> addFrame(LinkedList<Integer> linkedList, int count) {
        if (!linkedList.contains(list[count])) {
            linkedList.addLast(list[count]);
            len++;
            return linkedList;
        } else {
            return linkedList;
        }
    }

    /**
     * 帧表满时替换
     * @param linkedList 进程的帧表
     * @param count 计数器
     * @return 更新后的帧表
     */
    public LinkedList<Integer> replace(LinkedList<Integer> linkedList, int count) {
        if (!linkedList.contains(list[count])) { //不含需要的帧号
            linkedList.removeFirst();
            linkedList.addLast(list[count]);
            times++;
            return linkedList;
        } else {
            return linkedList;
        }
    }

    /**
     * 打印Page传进的列表
     */
    public void print() {
        System.out.println(Arrays.toString(list));
    }

    public int getTimes() {
        return times;
    }

    public int getLen() {
        return len;
    }


}
