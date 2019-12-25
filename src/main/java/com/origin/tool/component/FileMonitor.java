package com.origin.tool.component;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author lixiewen
 * @create 2019-12-23 22:14
 */
@Component
public class FileMonitor {
    /**
     * @description
     * @param
     * @return
     * @version 2.0, 2019/1/25 9:44
     * @author <a href="mailto:Tastill@**.cn">Tastill</a>
     */
//     @PostConstruct
    public void initFileMonitor() {
        // 监控目录
        String rootDir = "src/main/resources/";
        // 轮询间隔 5 秒
        Integer time = 1;
        long interval = TimeUnit.SECONDS.toMillis(time);
        // 创建一个文件观察器用于处理文件的格式,
        //                        FileFilterUtils.suffixFileFilter(".txt")
        FileAlterationObserver _observer = new FileAlterationObserver(
            rootDir,
            FileFilterUtils.and(
                FileFilterUtils.fileFileFilter()),  //过滤文件格式
            null);
        FileAlterationObserver observer = new FileAlterationObserver(rootDir);

        observer.addListener(new VueFileListener()); //设置文件变化监听器
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
