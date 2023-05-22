package zjut.replacementAlgorithm;

import java.util.Arrays;
import java.util.LinkedList;

public class LRU {
    private final int[] list;
    private int times; // 置换次数
    private int len; // 链表中页面个数

    /**
     * 得到Page生成的list
     *
     * @param pageList
     */
    public LRU(int[] pageList, int len) {
        list = pageList;
        times = 0;
        this.len = len;
    }

    /**
     * 前五次的请求调页
     * @param linkedList 进程的帧管理链表
     * @param count 计数器，进行到第几个帧了
     * @return 更新后的帧管理链表
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

    public LinkedList<Integer> replace(LinkedList<Integer> linkedList, int count) {
        //认为head是栈底，rear是栈顶
        if (!linkedList.contains(list[count])) {
            // 置换算法
            linkedList.addLast(list[count]);
            linkedList.removeFirst();
            times++;
            return linkedList;
        } else { // 换位操作，放到栈顶
            int index = 0; //存在帧号的index
            while (linkedList.get(index) != list[count]) {
                index++;
            }
            int value = linkedList.get(index);
            linkedList.addLast(value);
            linkedList.remove(index);
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
