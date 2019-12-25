package com.origin.tool.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lixiewen
 * @create 2019-10-17 14:20
 */
public class IOUtilTest {

    @Test
    public void getFiles() throws IOException {
        File f = new File("E:\\");
        System.out.println(f.getParent());
        List<File> files = new ArrayList();
        List<String> redundantFiles = new ArrayList();
        IOUtil.getFiles(f, files);
        Map<String,String> map = new HashMap<>();
        for (File file : files) {

            /*System.out.println(file.getName());
            // 文件全路径
            System.out.println(file.getAbsoluteFile());
            System.out.println(file.getParentFile());
            System.out.println(file.getParent());
            System.out.println(file.length());
            // 文件大小
            System.out.println(file.length()*0.0009766); // 1b = 0.0009766kb
            // 由于linux下不能获取文件的创建时间
            Path path= Paths.get(file.getAbsoluteFile().toString());
            BasicFileAttributeView basicview= Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS );
            BasicFileAttributes attr = basicview.readAttributes();
            String creationTime = dateStr(new Date(attr.creationTime().toMillis()));
            System.out.println(creationTime);
            String updateTime = dateStr(new Date(attr.lastModifiedTime().toMillis()));
            System.out.println(updateTime);
            String accessTime = dateStr(new Date(attr.lastAccessTime().toMillis()));
            System.out.println(accessTime);*/
            String fileMD5 = IOUtil.getFileMD5(file);
            System.out.println(file.getName()+":"+fileMD5);
            String filePath = map.get(fileMD5);
            if(filePath != null){
                redundantFiles.add(filePath);
                redundantFiles.add(file.getAbsolutePath());
            }else {
                map.put(fileMD5,file.getAbsolutePath());
            }
        }
        Collections.sort(redundantFiles);
        for (String redundantFile : redundantFiles) {
            System.out.println(redundantFile);
        }
    }
    public String dateStr (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }



    @Test
    public void test2() throws IOException {
        File f = new File("E:\\");
//        File parentFile = f.getParentFile();
//        File[] files = parentFile.listFiles();
        File[] files = f.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

}