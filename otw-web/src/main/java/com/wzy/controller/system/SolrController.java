package com.wzy.controller.system;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-03-06
 */
@Controller
@RequestMapping("/solr")
public class SolrController extends AbstractController {


    private static final Logger log = LoggerFactory.getLogger(SolrController.class);
    @Autowired
    private SolrClient client;

    /**
     * 新增/修改 索引
     * 当 id 存在的时候, 此方法是修改(当然, 我这里用的 uuid, 不会存在的), 如果 id 不存在, 则是新增
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", uuid);
            doc.setField("solr_title", "我是中国人, 我爱中国");

            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */

            client.add("articles", doc);
            //client.commit();
            client.commit("articles");
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(String id)  {
        try {
            client.deleteById("articles",id);
            client.commit("articles");
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 删除所有的索引
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(){
        try {
            client.deleteByQuery("articles","*:*");
            client.commit("articles");

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 根据id查询索引
     * @return
     * @throws Exception
     */
    @RequestMapping("getById")
    public String getById(String id) throws Exception {
        SolrDocument document = client.getById("articles", id);
        System.out.println(document);
        return document.toString();
    }

    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("search1")
    public Map<String, Map<String, List<String>>> search(){

        try {
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            params.set("q", "手机");

            //过滤条件
            params.set("fq", "product_price:[100 TO 100000]");

            //排序
            params.addSort("product_price", SolrQuery.ORDER.asc);

            //分页
            params.setStart(0);
            params.setRows(20);

            //默认域
            params.set("df", "product_title");

            //只查询指定域
            params.set("fl", "id,product_title,product_price");

            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField("product_title");
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");

            QueryResponse queryResponse = client.query(params);

            SolrDocumentList results = queryResponse.getResults();

            long numFound = results.getNumFound();

            System.out.println(numFound);
            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            for (SolrDocument result : results) {
                System.out.println(result.get("id"));
                System.out.println(result.get("product_title"));
                //System.out.println(result.get("product_num"));
                System.out.println(result.get("product_price"));
                //System.out.println(result.get("product_image"));

                Map<String, List<String>> map = highlight.get(result.get("id"));
                List<String> list = map.get("product_title");
                System.out.println(list.get(0));

                System.out.println("------------------");
                System.out.println();
            }
            return highlight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("search")
    public String searchPage(String condition, int start,int limit,String flag,Model model){
        try {
//            String enCondition = URLEncoder.encode(condition, "UTF-8");
            String enCondition = condition;
            SolrQuery params = new SolrQuery();
            //查询条件, 这里的 q 对应 下面图片标红的地方
            StringBuffer stringBuffer = new StringBuffer();
            if(StringUtils.isEmpty(condition)){
                params.set("q", "*:*");
            }else{
                stringBuffer.append("title:"+ enCondition +" OR ");
                stringBuffer.append("description:"+ enCondition +" OR ");
                stringBuffer.append("keywords:"+ enCondition );
                params.set("q", stringBuffer.toString());
                //默认查询的字段
//                params.set("df", "title");
            }
//            params.set("q", "*:*");
            //过滤条件
            //排序
//            params.addSort("product_price", SolrQuery.ORDER.asc);

            //分页
            params.setStart(start);
            params.setRows(limit);

            //默认域
//            params.set("df", "title");

            //只查询指定域
            params.set("fl", "id,title,createTime,tags,description,keywords,nickname,strategy");
            params.set("hl.fl","title,description,keywords");
            //高亮
            //打开开关
            params.setHighlight(true);
            params.setHighlightRequireFieldMatch(true);
            params.setHighlightFragsize(150);
            //设置前缀
            params.setHighlightSimplePre("<label style='color:#1A94E6'>");
            //设置后缀
            params.setHighlightSimplePost("</label>");

//            params.setHighlightFragsize(72);
//            params.setHighlightSnippets(3);
            QueryResponse queryResponse = client.query(params);

            SolrDocumentList results = queryResponse.getResults();

            long numFound = results.getNumFound();

            System.out.println(numFound);
            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();
            SolrDocumentList list = new SolrDocumentList();
            SolrDocument document = null;
            for(int i=0;i<results.size();i++) {
                document = results.get(i);
                Map<String, List<String>> idMap = highlight.get(document.getFieldValue("id"));
                if(CollectionUtils.isEmpty(idMap)){
                    document.setField("title",String.valueOf(document.getFieldValue("title")).replace(condition,"<label style='color:#1A94E6'>"+condition+"</label>"));
                    document.setField("description",String.valueOf(document.getFieldValue("description")).replace(condition,"<label style='color:#1A94E6'>"+condition+"</label>"));
                    document.setField("keywords",String.valueOf(document.getFieldValue("keywords")).replace(condition,"<label style='color:#1A94E6'>"+condition+"</label>"));
                }else{
                    if(Objects.nonNull(idMap.get("title"))){
                        document.setField("title", org.apache.commons.lang3.StringUtils.strip(String.valueOf(highlight.get(document.getFieldValue("id")).get("title")),"[]"));
                    }else{
                        document.setField("title",String.valueOf(document.getFieldValue("title")).replace(condition,"<label style='color:#1A94E6'>"+condition+"</label>"));
                    }
                    if(Objects.nonNull(idMap.get("description"))){
                        document.setField("description", org.apache.commons.lang3.StringUtils.strip(String.valueOf(highlight.get(document.getFieldValue("id")).get("description")),"[]"));
                    }else{
                        document.setField("description",String.valueOf(document.getFieldValue("description")).replace(condition,"<label style='color:#1A94E6'>"+condition+"</label>"));
                    }
                    if(Objects.nonNull(idMap.get("keywords"))){
                        document.setField("keywords", org.apache.commons.lang3.StringUtils.strip(String.valueOf(highlight.get(document.getFieldValue("id")).get("keywords")),"[]"));
                    }else{
                        document.setField("keywords",String.valueOf(document.getFieldValue("keywords")).replace(condition,"<label style='color:#1A94E6'>"+condition+"</label>"));
                    }
//                    document.setField("description", org.apache.commons.lang3.StringUtils.strip(String.valueOf(highlight.get(document.getFieldValue("id")).get("description")),"[]"));
//                    document.setField("keywords", org.apache.commons.lang3.StringUtils.strip(String.valueOf(highlight.get(document.getFieldValue("id")).get("keywords")),"[]"));

                }
                 list.add(document);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("records",list);
            map.put("total",numFound);
            map.put("condition",condition);
            model.addAttribute("resources",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "all".equals(flag)?"portal/search_blog::article_type":"portal/search_blog";
    }

    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("spellCheck")
    @ResponseBody
    public List<Map<String,String>> SpellCheck( String keywords){
        List<Map<String,String>> result = new ArrayList<>();
       try{
           SolrQuery params = new SolrQuery();
           params.setRequestHandler("/suggest");
           params.set("suggest.build", true);
           params.set("suggest.q", keywords);
           params.set("suggest.dictionary", "mySuggester");
           params.set("suggest", true);
           QueryResponse response = null;
           response = client.query(params);
           SuggesterResponse suggest = response.getSuggesterResponse();
           Map<String, List<String>> termMap = suggest.getSuggestedTerms();
           for (List<String> strs : termMap.values()) {
               for (String s : strs) {
                   Map<String,String> map = new HashMap<>();
                   map.put("java",s);
                   result.add(map);
                   System.out.println(s);
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

