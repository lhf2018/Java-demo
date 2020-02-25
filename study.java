package study;

import org.apache.commons.lang.math.RandomUtils;

import java.io.*;
import java.util.*;

public class study {
    public static void main(String[] args) throws IOException {
        File file=new File("E:\\study.txt");
        Map<Integer,String> questions=new HashMap<>();
        BufferedReader br=new BufferedReader(new FileReader(file));
        BufferedWriter bw1=new BufferedWriter(new FileWriter(new File("E:\\不会的题目.txt"),true));
        BufferedWriter bw2=new BufferedWriter(new FileWriter(new File("E:\\不熟悉的题目.txt"),true));

        String temp="";
        int index=-1;
        while ((temp=br.readLine())!=null){
            index=temp.indexOf(".");
            index=Integer.parseInt(temp.substring(0,index));
            questions.put(index,temp);
        }
        int que=-1;
        int ans=-1;
        int solved=0;
        List<String> listOfunfamiliar=new ArrayList<>();
        List<String> listOfnosolve=new ArrayList<>();
        while (true) {
            Scanner in=new Scanner(System.in);
            que= RandomUtils.nextInt(questions.size()+1);
            System.out.println(questions.get(que));
            System.out.println("1 会；2 不熟练；3 完全不会");
            while ((ans=in.nextInt())>3||ans<1)
                System.out.println("请重新输入");
            switch (ans){
                case 1:solved++;break;
                case 2:listOfunfamiliar.add(questions.get(que));
                break;
                case 3:listOfnosolve.add(questions.get(que));
                break;
            }
            if(listOfnosolve.size()>3||listOfunfamiliar.size()>5){
                System.out.println("对不起，面试失败");
                System.out.println("----不会的题目----");
                for(int i=0;i<listOfnosolve.size();i++){
                    System.out.println(listOfnosolve.get(i));
                    bw1.write(listOfnosolve.get(i)+"\n");
                }
                bw1.flush();
                System.out.println("----不熟悉的题目----");
                for(int i=0;i<listOfunfamiliar.size();i++){
                    System.out.println(listOfunfamiliar.get(i));
                    bw2.write(listOfunfamiliar.get(i)+"\n");
                }
                bw2.flush();
                System.out.println("是否重新开始面试? 1开启；2结束");
                int choose=in.nextInt();
                if(choose==2)break;
            }else if(solved>10){
                System.out.println("恭喜，面试通过");
                System.out.println("----面试总结：不会的题目----");
                for(int i=0;i<listOfnosolve.size();i++){
                    System.out.println(listOfnosolve.get(i));
                    bw1.write(listOfnosolve.get(i)+"\n");
                }
                bw1.flush();
                System.out.println("----面试总结：不熟悉的题目---- ");
                for(int i=0;i<listOfunfamiliar.size();i++){
                    System.out.println(listOfunfamiliar.get(i));
                    bw2.write(listOfunfamiliar.get(i)+"\n");
                }
                bw2.flush();
                System.out.println("是否开启下一轮? 1开启；2结束");
                int choose=in.nextInt();
                if(choose==2)break;
            }
        }
    }
}
