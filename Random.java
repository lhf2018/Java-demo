package random;


/**
 * 本demo采用【线性同余】实现随[0,bond)范围内随机数的生成和[0,1)范围内float数的生成
 * 公式：(seed*168071L)%((1<<31)-1);
 */
public class Random {
    /**
     * 测试结果：
     * num[0]: 9997625
     * num[1]: 9994308
     * num[2]: 10003140
     * num[3]: 10002049
     * num[4]: 10006536
     * num[5]: 10000060
     * num[6]: 9999241
     * num[7]: 10007455
     * num[8]: 9988819
     * num[9]: 10000767
     *
     */
    public static void main(String[] args) throws InterruptedException {
        int[] num=new int[10];
        Random random=new Random();
        for(int i=0;i<100000000;i++){
            num[random.nextInt(10)]++;
        }
        for(int i=0;i<10;i++){
//            System.out.println(random.next());
            System.out.println("num["+i+"]: "+num[i]);
        }
    }

    /**
     * 输出[0,bond)范围内
     * @param bond 边界
     */
    public int nextInt(int bond){
        long seed = System.nanoTime();
        long temp=(seed*168071L)%((1<<31)-1);
        return (int) (temp%bond);
    }

    /**
     * [0,1)范围内float数的生成
     */
    public float next(){
        long seed = System.nanoTime();
        long temp=(seed*168071L)%((1<<31)-1);
        return (float) (temp*1.0/((1<<31)-1));
    }

}
