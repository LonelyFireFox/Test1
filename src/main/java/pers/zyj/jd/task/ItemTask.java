/*
package pers.zyj.jd.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.zyj.jd.bean.Item;
import pers.zyj.jd.serviceImpl.ItemServiceImpl;
import pers.zyj.jd.utils.HttpUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class ItemTask {

    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private ItemServiceImpl itemService;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    */
/**
     * 当下载任务完成后间隔多长时间进行下次任务
     *//*

    @Scheduled(fixedDelay = 100 * 1000)
    public void itemTask(){
        //声明需要解析的初始地址,抓取的数据是多页的，所以这里要进行page的变量化
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&page=";

        //按照页面对手机的搜索结果进行遍历解析
        for (int i = 1; i <10; i+=2){
            String html = httpUtils.doGetHtml(url + i);
            //解析页面，获取商品数据并存储
            try {
                this.parse(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    */
/**
     * 解析页面，获取商品数据并存储解析页面，获取商品数据并存储
     * @param html
     *//*

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

           */
/* String attr = skuElement.attr("data-sku");
            //这里拿到30条数据是对的，因为京东是懒加载，还有一半的数据没有拿到，应该是涉及到动态加载的问题，待研究
            System.out.println("~~~attr = " + attr+"count:"+(++count));*//*


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
                String itemInfo = this.httpUtils.doGetHtml(itemUrl);
                //也可以直接访问url
                Document docx = Jsoup.parse(itemInfo);
                String itemTitle = docx.getElementsByClass("sku-name").text();
                System.out.println("itemTitle = " + itemTitle);
                item.setTitle(itemTitle);

                // 商品价格
                String priceJson = this.httpUtils.doGetHtml("https://p.3.cn/prices/mgets?skuIds=J_" + sku);
                double itemPrice = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(itemPrice);

                // 商品图片
                //https://img13.360buyimg.com/n9/jfs/t1/27282/9/12522/189389/5c999638E1ad482f0/37adf85d1e3f7596.jpg
                String src = ele.attr("data-lazy-img");
                src = src.replace("/n9/", "/n1/");
                String picName = this.httpUtils.doGetImage("https:"+src);
                item.setPic(picName);

                // 创建时间
                item.setCreated(new Date());

                // 更新时间
                //其实这里一次创建就不会更新，所以只是为了保持更新和修改一致
                item.setUpdated(item.getCreated());

                itemService.save(item);
            }
        }
    }

}
*/
