package zjut.replacementAlgorithm;

import java.util.Arrays;
import java.util.LinkedList;

public class OPT {
    private final int[] list;
    private int times; // 置换次数
    private int len; // 链表中页面个数

    /**
     * 得到Page生成的list
     *
     * @param pageList
     */
    public OPT(int[] pageList, int len) {
        list = pageList;
        times = 0;
        this.len = len;
    }


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
     * OPT替换算法
     * @param linkedList
     * @param count
     * @return 更新后的帧链表
     */
    public LinkedList<Integer> replace(LinkedList<Integer> linkedList, int count) {
        if (!linkedList.contains(list[count])) {
            int[] fraquency = {-1, -1, -1, -1, -1};
            for (int i = 0; i < 5; i++) {
                int distance;
                for (int j = count; j < list.length; j++) {
                    if (linkedList.get(i) == list[j])
                        fraquency[i] = j;
                }
            }
            int minIndex = Arrays.stream(fraquency).min().getAsInt(); // might be -1
            int realMinIndex = minIndex; // wont be -1
            if (minIndex == -1) {
                for (int i = 0; i < 5; i++) {
                    if (fraquency[i] == -1)
                        realMinIndex = i;
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    if (fraquency[i] == minIndex)
                        realMinIndex = i;
                }
            }
            linkedList.remove(realMinIndex);
            linkedList.add(realMinIndex, list[count]);
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

    /**
     * 统计页面置换的次数
     *
     * @return
     */
    public int getTimes() {
        return times;
    }

    /**
     * 统计此时链表中页面的个数
     *
     * @return
     */
    public int getLen() {
        return len;
    }
}
