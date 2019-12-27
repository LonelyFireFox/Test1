package pers.zyj.jd.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


@Component
public class HttpUtils {
    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        this.cm.setMaxTotal(100);

        //设置每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(100);
    }

    /**
     * 根据请求地址下载页面数据
     *
     * @param url
     * @return String 页面数据
     */
    public String doGetHtml(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //创建httpGet请求对象,设置url地址
        HttpGet httpGet = new HttpGet(url);

        //设置相应请求头的信息，否则京东不给爬
        httpGet.addHeader(new BasicHeader(
                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0"
        ));
        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();

                String content = EntityUtils.toString(httpEntity, "utf8");
                //System.out.println("content = " + content);
                return content;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取页面失败
        return "获取页面失败";
    }

    /**
     * 下载图片
     * @param url
     * @return String 图片名称
     */
    public String doGetImage(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        //创建httpGet请求对象,设置url地址
        HttpGet httpGet = new HttpGet(url);

        //设置相应请求头的信息，否则京东不给爬
        httpGet.addHeader(new BasicHeader(
                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0"
        ));

        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;

        try {
            //使用HttpClient发起请求，获取响应
            response = httpClient.execute(httpGet);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空
                if (response.getEntity() != null) {
                    //下载图片
                    //获取图片的后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    //创建图片名，重命名图片
                    String picName = UUID.randomUUID().toString() + extName;
                    //下载图片
                    //使用IO
                    OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\WebMagic\\image\\" + picName));
                    response.getEntity().writeTo(outputStream);
                    //返回图片名称
                    return picName;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果下载图片失败
        return "";
    }

    /**
     * 设置请求信息
     * @return RequestConfig
     */
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                //创建连接的最长时间
                .setConnectTimeout(1000)
                //获取连接的最长时间
                .setConnectionRequestTimeout(500)
                //数据传输的最长时间
                .setSocketTimeout(10000)
                .build();

        return config;
    }

    public void doGetImage(String prcHref, String picName,String path) {

        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(prcHref);
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                if (httpResponse.getEntity() != null) {
                    //下载图片,使用IO保存到本地
                    OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\Linkey\\image\\" + path+"\\"+picName));
                    httpResponse.getEntity().writeTo(outputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
