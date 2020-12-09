package ThreadSumNum;
import java.util.Scanner;
public class ThreadTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入所需数字：");
        int x = sc.nextInt();
        sc.close();

        MyThread myThread1 = new MyThread(0,250000000, x,1);
        MyThread myThread2 = new MyThread(250000001, 500000000, x, 2);
        MyThread myThread3 = new MyThread(500000001, 750000000, x, 3);
        MyThread myThread4 = new MyThread(750000001, 1000000000-1, x, 4);
        //new出4个线程
        Thread t1 = new Thread(myThread1);
        t1.start();
        t1.setName("t1线程");
        Thread t2 = new Thread(myThread2);
        t2.start();
        t2.setName("t2线程");
        Thread t3 = new Thread(myThread3);
        t3.start();
        t3.setName("t3线程");
        Thread t4 = new Thread(myThread4);
        t4.start();
        t4.setName("t4线程");

        Thread t5 = new Thread(new MyThread(0, 0,0, 0) { //定义一个t5线程
            public void run() {
                while(true) {
                    if((t1.isAlive()||t2.isAlive()||t3.isAlive()||t4.isAlive())==false) {
                        System.out.println("Sum = " + (ans1+ans2+ans3+ans4)); //所有线程执行完，输出最终结果
                        break; //输出成功，跳出循环
                    }else {
                        try {
                            Thread.sleep(200); //降低while判断的频率
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        t5.setPriority(1); //设置t5线程的优先级为最低
        t5.start();
    }

    static long ans1;
    static long ans2;
    static long ans3;
    static long ans4;

    static class MyThread implements Runnable { //定义一个类实现Runnable接口
        private long start;
        private long end;
        private int x; //用户输入
        private int no; //第几个线程

        public MyThread(long start, long end, int x, int no) {
            this.start = start;
            this.end = end;
            this.x = x;
            this.no = no;
        }

        @Override
        public void run() {
            long res = 0;
            for (long num = start; num <= end; num++) {
                if(contain(num,x)) res += num;
            }

            if(no == 1) ans1 = res;
            else if(no == 2) ans2 = res;
            else if(no == 3) ans3 = res;
            else ans4 = res;
            System.out.println(Thread.currentThread().getName() + "已完成"); //输出已完成的线程
        }
    }

    private static boolean contain(long num, int x) {
        return String.valueOf(num).contains(String.valueOf(x));
    }


}



