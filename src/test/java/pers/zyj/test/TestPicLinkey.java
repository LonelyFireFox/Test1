package pers.zyj.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class TestPicLinkey {

    @Test
    public void getPic(){
        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://39.108.155.170/wcp-web4.0.5/actionImg/Publoadimg.do?id=2c92272d66c8efe7016759a127be061e");

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);

            if(httpResponse.getStatusLine().getStatusCode() == 200){
                if (httpResponse.getEntity() != null) {
                    //下载图片
                    //创建图片名，重命名图片
                    String picName = UUID.randomUUID().toString() + ".png";
                    //下载图片,使用IO保存到本地
                    OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\Linkey\\image\\" + picName));
                            httpResponse.getEntity().writeTo(outputStream);
                    System.out.println("you are suceess! = ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
