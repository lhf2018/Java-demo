import java.io.File;
import java.util.*;

/**
 * 此例使用java线程监控文件目录变化的实现方法
 *
 */
public class FilesMonitor implements Runnable {
    //文件路径
    private String filepath="C:\\Users\\Administrator\\Desktop\\a\\";
    private static Map<String,File> map=new HashMap<>();
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(3000);
                FileMonitor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件监听
     */
    public void FileMonitor() {
        File[] files=getFile(filepath,null);
        if(files!=null&&files.length>0){
            //如果缓存中文件与读取的个数不一样的时候
            String fName="";
            if (files.length != map.size()) {
                if (map.size() == 0) {
                    for (File file : files) {
                        fName = file.getName();
                        map.put(fName, file);
                        System.out.println("新增了文件：" + fName);
                    }
                } else {
                        Set<File> set = new HashSet<>(Arrays.asList(files));//保存文件夹中的文件
                        List<String> remove_list=new ArrayList<>();//保存被删除的文件名
                        for (String str : map.keySet()) {
                            if (!set.contains(map.get(str))) {
                                remove_list.add(str);
                                System.out.println("减少了文件：" + str);
                            }
                        }
                        //删除map中的文件
                        for(String str:remove_list){
                            map.remove(str);
                        }
                        //查看新增的文件
                        for (File file : set) {
                            if (!map.containsKey(file.getName())) {
                                map.put(file.getName(), file);
                                System.out.println("新增了文件：" + file.getName());
                            }
                        }
                }
            }
        }
        System.out.println("此时缓存中文件个数：" + map.size());
    }

    /**
     * 文件读取
     */
    private File[] getFile(String filepath, String filename) {
        File[] files=null;
        if(filename==null){
            File doc=new File(filepath);
            if(doc.isDirectory()){
                String[] filenameArr=doc.list();//返回路径下的所文件
                if(filenameArr.length>0){
                    files=new File[filenameArr.length];
                    for(int i=0;i<filenameArr.length;i++){
                        filename=filenameArr[i];
                        String fileAbsPath=filepath+filename;
                        files[i]=new File(fileAbsPath);
                    }
                }
            }
        }else{
            String path=filepath+filename;
            File doc=new File(path);
            if(doc.isFile()){
                files=new File[1];
                files[0]=doc;
            }
        }
        return files;
    }
    public void show(){
        FilesMonitor filesMonitor=new FilesMonitor();
        Thread thread=new Thread(filesMonitor);
        thread.setName("bupt");
        thread.start();
    }

    public static void main(String[] args) {
        FilesMonitor f=new FilesMonitor();
        f.show();
    }
}
