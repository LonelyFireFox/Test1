package pers.zyj.jd.task;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.zyj.jd.utils.HttpUtils;

import java.io.File;


@Component
public class PicLinkeyTask {
    /**
     * 爬取图片思路：
     * 1. 爬取左边目录所有链接，先存储在数据结构a中
     * 2. 遍历a中的每一项，获取其所有子文章链接b,包括多页信息
     * 3. 访问b中的所有链接，下载页面解析其附件图片唯一ID，再进行访问下载并保存到本地
     *
     */
    @Autowired
    private HttpUtils httpUtils;

    @Scheduled(fixedDelay = 100 * 1000)
    public void picLinkeyTask() {
        //声明需要解析的初始地址,抓取的数据是多页的，所以这里要进行page的变量化
        String url = "http://39.108.155.170/wcp-web4.0.5/webtype/viewNONE/Pub";
        //14.html"

        for (int i = 1; i <=14; i ++) {
            String html = httpUtils.doGetHtml(url + i +".html");
            //解析页面，获取商品数据并存储
            this.parse(html);
        /*    try {

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

    }

    /**
     * 解析当前页的所有子链接并访问，然后获取图片链接，下载到本地
     * @param html
     */
    private void parse(String html) {
            Document doc = Jsoup.parse(html);
            //解析子链接
            Elements elements = doc.getElementsByClass("stream-item");
            System.out.println("elements.size() = " + elements.size());

            for (Element e:elements) {
                Elements subLink = e.select("div.media-body a.doc_node_title");
                String subHref = subLink.attr("href");
                subHref = "http://39.108.155.170/wcp-web4.0.5/" + subHref;
                System.out.println("href = " + subHref);
                //拿到子链接后，就要继续访问它
                String realPage = httpUtils.doGetHtml(subHref);
                //解析真实文章页面的附件，获取图片链接
                Document realDoc = Jsoup.parse(realPage);
                //获取文章标题
                String doc_title = realDoc.getElementsByClass("doc_title").text();
                //获取文章分类
                String doc_category = realDoc.select("div.doc-conentinfo a").text();

                String path = doc_category+"--"+doc_title;
                System.out.println("path = " + path);
                Elements picEles = realDoc.select("div#docFilesListPId p a");
                //需要为每篇文章创建一个图片文件夹,以文章标题加分类作为文件夹名称
                File dir = new File("C:\\Users\\Administrator\\Desktop\\Linkey\\image\\" + doc_category+"--"+doc_title);

                if (dir.mkdirs()) {
                    System.out.println("创建目录" + path + "成功！");
                } else {
                    System.out.println("创建目录" + path + "失败！");
                }

                for (Element p : picEles) {
                    //获取连接
                    String prcHref = p.attr("href");
                    //对连接进行处理获取图片ID
                    String substring = prcHref.substring(19);
                    substring = substring.substring(0, substring.lastIndexOf("."));
                    prcHref = "http://39.108.155.170/wcp-web4.0.5/actionImg/Publoadimg.do?id=" + substring;
                    System.out.println("prcHref = " + prcHref);
                            //获取每张图片名字
                    String picName = p.text();
                    System.out.println("picName = " + picName);
                    //保存图片
                    httpUtils.doGetImage(prcHref,picName, path);
                }

            }
        }
    }


