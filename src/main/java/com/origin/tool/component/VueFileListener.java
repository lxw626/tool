package com.origin.tool.component;


import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;


/**
 * @author lixiewen
 * @create 2019-12-23 22:15
 */

public class VueFileListener extends FileAlterationListenerAdaptor {

    /**
     * @description 启动监听
     * @param
     * @return
     * @version 2.0, 2019/1/24 15:08
     * @author <a href="Tastill@**.cn">Tastill</a>
     */
    @Override
    public void onStart(FileAlterationObserver observer) {
        // System.out.println("启动监听器：");
    }
    @Override
    public void onDirectoryCreate(File directory) {
        System.out.println("有新文件夹生成："+directory.getName());
    }
    @Override
    public void onDirectoryChange(File directory) {
        System.out.println("有文件夹内容发生变化："+directory.getName());
    }
    @Override
    public void onDirectoryDelete(File directory) {
        System.out.println("有文件夹被删除："+directory.getName());
    }

    /**
     * 有新的.vue文件生成时,解析成VueVomponent组件并接入VueComponentMap
     * @param file
     */
    @Override
    public void onFileCreate(File file){
        if(file.getName().endsWith(".vue")){
//            VueComponentMap.updateVueComponent(file);
//            VueComponentMap.indexGenerator();
        }

        System.out.println("有新文件生成："+file.getName());
    }

    /**
     * 当.vue文件内容发生改变时(idea需要手动编译才能检测到)更新VueComponentMap
     * @param file
     */
    @Override
    public void onFileChange(File file){
        if(file.getName().endsWith(".vue")){
//            VueComponentMap.updateVueComponent(file);
//            VueComponentMap.indexGenerator();
        }
        System.out.println("有文件被修改："+file.getName());
//        super.onFileChange(file);
    }

    /**
     * 当文件被删除时删除对应的VueComponent
     * @param file
     */
    @Override
    public void onFileDelete(File file){
        if(file.getName().endsWith(".vue")){
            String fullName = file.getAbsolutePath();
//            VueComponentMap.removeVueComponent(fullName);
//            VueComponentMap.indexGenerator();
        }
        System.out.println("有文件被删除："+file.getName());
    }
    /**
     * @description 监听停止
     * @param
     * @return
     * @version 2.0, 2019/1/24 15:07
     * @author <a href="Tastill@**.cn">Tastill</a>
     */
    @Override
    public void onStop(FileAlterationObserver observer){
        // System.out.println("监听停止");
    }
}
