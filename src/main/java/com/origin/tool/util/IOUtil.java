package com.origin.tool.util;


import com.origin.tool.config.GConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {
    //获取pw
    public static PrintWriter getPrintWriter(File file) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pw;
    }

    //根据给定的文件路径创建文件
    public static File getFile(String path) {
        File file = new File(path);
//        如果文件路径不存在就创建路径
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
//        如果文件不存在就创建文件,如果文件存在就删除重新创建新文件
        if (file.exists()) {
            file.delete();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 将字符串输出到指定的位置
     *
     * @param path
     * @param str
     */
    public static void print2File(String path, String str) {
        File file = getFile(path);
        PrintWriter pw = getPrintWriter(file);
        if(str!=null){
            pw.print(str);
            pw.flush();
        }
        pw.close();
        System.out.println("文件:" + path + "生成完毕");
    }
    public static void print2RunTxt( String str) {
        String runPath = GConfig.runPath;
        print2File(runPath,str);
    }

    /**
     * 扫描指定路径下的文件
     *
     * @param file
     * @param files
     */
    public static List<File> getFiles(File file, List<File> files) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isFile()) {
                files.add(f);
            } else {
                getFiles(f, files);
            }
        }
        return files;
    }

    /**
     * 逐行读取
     *
     * @param fin
     * @throws IOException
     */
    public static List<String> readFileByLine(File fin) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fin));
        String line = null;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        return list;
    }

}
