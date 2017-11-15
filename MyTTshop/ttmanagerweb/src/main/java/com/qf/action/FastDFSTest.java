package com.qf.action;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSTest {
    public void testFast() throws IOException, MyException {
        //加载配置文件,初始化配置文件
        ClientGlobal.init("E:\\IdeaSpace2\\MyTTshop\\ttmanagerweb\\src\\main\\resources\\client.conf");//复制全路径
        //创建跟踪器的客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //创建跟踪器的服务端对象
        TrackerServer connection=trackerClient.getConnection();
        //声明一个存储器的服务端对象
        StorageServer storageServer=null;//仅声明即可
        //创建存储器的客户端对象
        StorageClient storageClient=new StorageClient(connection,storageServer);//需要两个参数，前者为跟踪器的服务端对象，后者为存储器的服务端对象
        //调用存储器酷护短对象的上传方法
        String[] jpgs = storageClient.upload_file("C:\\Users\\Administrator\\Desktop\\u=626237007,2604758811&fm=58.jpg", "jpg", null);//需要三个参数，第一个参数为图片所在地址，第二个为后缀名，第三个为null
        //服务端返回了完整的路径信息
//        for (String jpg : jpgs) {
//            System.out.println(jpg);//编号0为组名group1，编号1为名字M00/00/00/rBG9yVoK7viAZE-kAAAu_JbCc9s913.jpg
//        }
        String url="http://47.95.210.128:88/"+jpgs[0]+"/"+jpgs[1];
        System.out.println(url);
    }

    //调用上述方法
    public static void main(String[] args) {
        FastDFSTest fastDFSTest=new FastDFSTest();
        try {
            fastDFSTest.testFast();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
