package com.zlljesse.practise.simple_width_crawler;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zliang on 2016/4/7.
 */
public class HtmlParserTool {

    /**
     * 获取一个网站的链接
     * @param url
     * @return
     */
    public static Set<String> extractLinks(String url,LinkFilter filter){
        Set<String> links = new HashSet<String>();
        try {
            Parser parser = new Parser(url);
            parser.setEncoding("gb2312");
            //过来frame标签的filter,用来获取frame的src
            NodeFilter frameFilter = new NodeFilter() {
                public boolean accept(Node node) {
                    if (node.getText().startsWith("frame src=")){
                        return true;
                    }else{
                        return false;
                    }
                }
            };
            //orFilter用来设置过滤<a>标签和<frame>标签
            OrFilter orFilter = new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);
            //得到所有经过过滤的标签
            NodeList list = parser.extractAllNodesThatMatch(orFilter);
            for (int i=0;i<list.size();i++){
                Node tag = list.elementAt(i);
                if(tag instanceof LinkTag){//<a>标签
                    LinkTag link = (LinkTag) tag;
                    String linkUrl = link.getLink(); //URL
                    //过滤url
                    if(filter.accept(linkUrl)){
                        links.add(linkUrl);
                    }

                }else {//<frame>标签
                    String  frame = tag.getText();
                    int start = frame.indexOf("src=");
                    frame = frame.substring(start);
                    int end = frame.indexOf(" ");
                    if(end == -1){
                        end = frame.indexOf(">");
                    }
                    String frameUrl = frame.substring(5,end -1);
                    //过滤url
                    if(filter.accept(frameUrl)){
                        links.add(frameUrl);
                    }
                }
            }

        }catch (ParserException e){
            e.printStackTrace();
        }

        return links;
    }
}
