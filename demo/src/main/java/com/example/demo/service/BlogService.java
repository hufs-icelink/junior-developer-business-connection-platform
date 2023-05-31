package com.example.demo.service;


import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SummarizeService summarizeService;



    public ArrayList<String[]> crawlingVelog(HttpServletRequest request) throws IOException {


        HttpSession session = request.getSession();
        String tistoryUserId = (String) session.getAttribute("blogId");

        ArrayList<Element> url_list = new ArrayList<>();
        ArrayList<String> duplicated_list = new ArrayList<>();

        String tistoryUrl_origin = "https://" + tistoryUserId + ".tistory.com";

        /*
        <a href="/?page=" 으로 페이지를 이동하면서 다 찾아줘야 할 듯
        현재는 첫번째 페이지에 한해서만 나오는 중
        */
        int page_count = 1;
        while(page_count <= 10) {
            String tistoryUrl = tistoryUrl_origin + "/?page=" + String.valueOf(page_count);
            System.out.println("tistory Url is : " + tistoryUrl);
            Document document = Jsoup.connect(tistoryUrl).get();

            Elements tagA = document.select("a");


            for (Element element : tagA) {
                String tempElement = element.attr("href").toString();
                if (tempElement.contains("category") || tempElement.contains("github") || tempElement.contains("page")
                        || tempElement.contains("archive") || tempElement.contains("http") || tempElement.contains("tag") ||
                        tempElement.contains("#") || tempElement.equals("") || tempElement.equals("/")
                ) {
                    continue;
                }

                //if(element.text().contains("html")) {
                //   System.out.println(element);
                // }

                if(!duplicated_list.contains(tempElement)) {
                    duplicated_list.add(tempElement);
                    url_list.add(element);
                }
            }

            if(url_list.size() > 10) {
                break;
            }

            page_count += 1;
        }

        String [] url = new String [10];
        String [] title = new String [10];
        String [] contents = new String [10];
        for (int i = 0; i < 10; i++) {
            url[i] =  tistoryUrl_origin + url_list.get(i).attr("href");
            title[i] = url_list.get(i).text();
        }

        for( String temp : url) {
            System.out.println(temp);
        }

        ArrayList<String[]> temp = new ArrayList<>();


        for(int i = 0; i < 10; i++) {

            try {
                Document document = Jsoup.connect(url[i]).get();
                contents[i]= document.toString();
            }
            catch (Exception e) {
                contents[i] = e.toString();
            }

        }

        temp.add(url);
        temp.add(title);
        temp.add(contents);

        return temp;
    }

    public void saveSummarize(HttpServletRequest request) throws IOException {
        ArrayList<String []> blogContents = crawlingVelog(request);
        String summarizeContents = null;
        String [] url = blogContents.get(0);
        String [] title = blogContents.get(1);
        String [] contents = blogContents.get(2);

        HttpSession session = request.getSession();
        User user = userRepository.findById((String) session.getAttribute("id")).orElseGet(()-> {
            return null;
        });
        for(int i = 0; i < 10; i++) {
            Blog blog = new Blog();
            summarizeContents = summarizeService.summarize(contents[i]);

            System.out.println(summarizeContents);
            blog.setUser(user);
            blog.setContent(summarizeContents);
            blog.setTitle(title[i]);
            blog.setLink(url[i]);
            blog.setType(0); // tistory = 0

            blogRepository.save(blog);
        }
    }



}
