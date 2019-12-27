package pers.zyj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.zyj.jd.bean.Item;
import pers.zyj.jd.serviceImpl.ItemServiceImpl;
import pers.zyj.jd.utils.HttpUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 京东网站爬取测试
 */
public class TestJD {

    private ItemServiceImpl itemService = new ItemServiceImpl();
    private HttpUtils httpUtils = new HttpUtils();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void getHtml() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&page=1");
        httpGet.addHeader(new BasicHeader(
                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0"
        ));
        HttpResponse httpResponse = httpClient.execute(httpGet);

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf8");

            //System.out.println("content = " + content);
            //parse(content);
            testGetImage(content);
        }


    }


    /**
     * 解析页面，获取商品数据并存储解析页面，获取商品数据并存储
     *
     * @param html
     */

    private void parse(String html) throws IOException {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("li.gl-item");
        //System.out.println("-----------------------------------------------");
        //System.out.println("elements = " + elements.toString());
        //一个页面有多个商品（li），所以用到循环
        int count = 0;
        for (Element skuElement : elements) {
            //spu商品集合
            Long spu = Long.parseLong(skuElement.attr("data-spu"));

           /* String attr = skuElement.attr("data-sku");
            //这里拿到30条数据是对的，因为京东是懒加载，还有一半的数据没有拿到，应该是涉及到动态加载的问题，待研究
            System.out.println("~~~attr = " + attr+"count:"+(++count));*/

            // 商品最小品类单元
            Elements spuEle = skuElement.select("ul.ps-main li a img");
            //System.out.println("minSkuEle.toString() = " + minSkuEle.toString());
            for (Element ele : spuEle) {
                Long sku = Long.parseLong(ele.attr("data-sku"));
                System.out.println("---minSku = " + sku);

                //根据sku（商品的唯一标识）查询数据
                Item item = new Item();
                item.setSku(sku);
                List<Item> list = this.itemService.findAll(item);
                //商品已存在
                if (list.size() > 0) {
                    continue;
                }
                //设置商品的spu
                item.setSpu(spu);

                // 商品详情地址
                String itemUrl = "https://item.jd.com/" + sku + ".html";
                item.setUrl(itemUrl);

                //商品标题
                String itemInfo = this.httpUtils.doGetHtml("itemUrl");
                Document docx = Jsoup.parse(itemInfo);//也可以直接访问url
                String itemTitle = docx.getElementsByClass("sku-name").text();
                item.setTitle(itemTitle);

                // 商品价格
                String priceJson = this.httpUtils.doGetHtml("https://p.3.cn/prices/mgets?skuIds=J_" + sku);
                double itemPrice = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(itemPrice);

                // 商品图片
                //https://img13.360buyimg.com/n9/jfs/t1/27282/9/12522/189389/5c999638E1ad482f0/37adf85d1e3f7596.jpg
                String src = doc.getElementsByClass("data-presale").attr("src");
                src = src.replace("/n9/", "/n1/");
                String picName = this.httpUtils.doGetImage(src);
                item.setPic(picName);

                // 创建时间
                item.setCreated(new Date());

                // 更新时间
                //其实这里一次创建就不会更新，所以只是为了保持更新和修改一致
                item.setUpdated(item.getCreated());

                System.out.println("item.toString() = " + item.toString());
                //itemService.save(item);
            }
        }
    }


    /**
     * 测试获取图片
     */
    public void testGetImage(String html) throws IOException {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("li.gl-item");
        //System.out.println("-----------------------------------------------");
        //System.out.println("elements = " + elements.toString());
        //一个页面有多个商品（li），所以用到循环
        int count = 0;
        for (Element skuElement : elements) {
            //spu商品集合
            Long spu = Long.parseLong(skuElement.attr("data-spu"));
            // 商品最小品类单元
            Elements spuEle = skuElement.select("ul.ps-main li a img");
            //System.out.println("minSkuEle.toString() = " + minSkuEle.toString());
            for (Element ele : spuEle) {
                Long sku = Long.parseLong(ele.attr("data-sku"));
                System.out.println("---minSku = " + sku);
                System.out.println("ele.toString() = " + ele.toString());

                //https://img13.360buyimg.com/n9/jfs/t1/27282/9/12522/189389/5c999638E1ad482f0/37adf85d1e3f7596.jpg
                String src = ele.attr("data-lazy-img");
                src = src.replace("/n9/", "/n1/");
                //String picName = this.httpUtils.doGetImage(src);
                System.out.println("src = " + src);
            }
        }
        }
    }
