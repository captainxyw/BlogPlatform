import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.Forum;
import com.itshidu.web.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Package:PACKAGE_NAME
 * Description:
 *
 * @Date:2020/1/31 19:09
 * @Author:xuyewei
 */

public class Test2 {

    public static void main(String[] args) throws IOException, ParseException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
        ArticleDao dao = ctx.getBean(ArticleDao.class);


        Forum forum = new Forum();
        forum.setId(2l);

        User user = new User();
        user.setId(5l);


        for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup.connect("https://bbs.csdn.net/forums/Java?page=" + i).get();

            Elements elements = doc.getElementsByClass("forums_title");
            for (Element element : elements) {

                String url = "https://bbs.csdn.net" + element.attr("href");
                System.out.println(element.text());
                System.out.println(url);


                //进入文章详情
                Document doc2 = Jsoup.connect(url).get();
                Element firstFloor = doc2.getElementsByClass("mod_topic_wrap").first();

                Element time = firstFloor.getElementsByClass("date_time").first();

                Element e2 = doc2.getElementsByClass("post_body").first();
                System.out.println(e2.html());


                Element forums_reply = element.parent().parent().getElementsByClass("forums_reply").first();
                Element reply_num = forums_reply.getElementsByClass("reply_num").first();
                String replyCount = reply_num.text().substring(reply_num.text().indexOf("/")+1);
                System.out.println(replyCount);
                System.out.println("----------------------------------------------------");


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Article a = new Article();
                e2.getElementsByTag("script").remove();
//                String text = StringEscapeUtils.escapeHtml4(e2.html());
                a.setContent(e2.html());
                a.setCreateTime(sdf.parse(time.text()));
                a.setHits(Long.parseLong(replyCount));
                a.setTitle(element.text());
                a.setUser(user);
                a.setForum(forum);

                dao.save(a);

            }

        }
    }
}
