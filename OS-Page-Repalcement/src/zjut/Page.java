package zjut;

import zjut.replacementAlgorithm.FIFO;

import java.util.Random;

public class Page {
    private int pageNum;
    private int[] pageList;


    public Page(int pageNum) {
        this.pageNum = pageNum;
        pageList = new int[pageNum];
    }


    /**
     * 得到随机生成的页面访问序列
     * @param min 页号最小值
     * @param max 页号最大值
     * @return 页号的数组
     */
    public int[] getPageList(int min, int max) {
        Random random = new Random();
        for (int i = 0; i < pageNum; i++) {
            int randNum = random.nextInt(max - min + 1) + min;
            pageList[i] = randNum;
        }
        return pageList;
    }




    /**
     * 打印
     */
    public void printList() {
        for (int content : pageList)
            System.out.print(content + " ");
    }

}
