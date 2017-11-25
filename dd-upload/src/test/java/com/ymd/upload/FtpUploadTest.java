package com.ymd.upload;

import com.ymd.ddshop.common.util.FtpUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FtpUploadTest {
/*    @Test
    public void textFTP()throws Exception{
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.174.133",21);
        ftpClient.login("ftpuser","ymd950819");

        FileInputStream fileInputStream = new FileInputStream(new File("g:\\QQ.jpg"));

        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        ftpClient.storeFile("hello.jpg",fileInputStream);

        fileInputStream.close();
        ftpClient.logout();
    }

    @Test
    public void testFtpUtils()throws Exception{
        FileInputStream fs = new FileInputStream(new File("g:\\QQ.jpg"));
        FtpUtils.uploadFile("192.168.174.133",21,"ftpuser","ymd950819","/home/ftpuser/www/images","/2017/11/16","hello2.jpg",fs);
        fs.close();
    }*/
}
