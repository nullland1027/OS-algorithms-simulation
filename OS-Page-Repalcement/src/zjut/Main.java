package zjut;


public class Main {
    public static double fifo() {
        Process a = new Process();
        System.out.print("帧号的随机序列为: ");
        a.printRandomListFIFO();
        System.out.println();

        for (int i = 0; i < Process.PAGENUMBER; i++) {
            System.out.print("请求调页第 " + (i + 1) + " 次");
            a.letFIFODo();
            System.out.println();
            a.printLinkedListFIFO();
        }
        return a.printRateFIFO();
    }

    public static double opt() {
        Process a = new Process();
        System.out.print("帧号的随机序列为: ");
        a.printRandomListOPT();
        System.out.println();

        for (int i = 0; i < Process.PAGENUMBER; i++) {
            System.out.print("请求调页第 " + (i + 1) + " 次");
            a.letOPTDo();
            System.out.println();
            a.printLinkedListOPT();
        }
        return a.printRateOPT();
    }

    private static double lru() {
        Process a = new Process();
        System.out.print("帧号的随机序列为: ");
        a.printRandomListLRU();
        System.out.println();

        for (int i = 0; i < Process.PAGENUMBER; i++) {
            System.out.print("请求调页第 " + (i + 1) + " 次");
            a.letLRUDo();
            System.out.println();
            a.printLinkedListLRU();
        }
        return a.printRateLRU();
    }
    public static void main(String[] args) {
        //opt();
        //fifo();
        //lru();
        double sum = 0.0;
        int cishu = 1000;
        for (int i = 0; i < cishu; i++) {
            sum += opt();
        }
        System.out.println(cishu + "次实验的平均缺页率为: " + sum * 1.0 / cishu);
    }

}
