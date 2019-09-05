package com.wzy.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * 类功能说明: 子公司数据同步
 * 类修改者	创建日期2019/2/27
 * 修改说明
 * <p>Title: HttpClientUtil.java</p>
 *
 * @author Wzy
 * @version V1.0
 **/
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
     /**
     * 带参 get请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, String> param) {
        //创建httpClient
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (Objects.nonNull(param)) {
                for (String key : param.keySet()) {
                    uriBuilder.addParameter(key, param.get(key));
                }
            }
            URI uri = uriBuilder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Cookie","JSESSIONID=FF8A38A86462286FFC4DE4A28ECAA9F4");
            response = aDefault.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                aDefault.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return resultString;
    }

    /**
     * 不带参数
     * @param url
     * @return
     */
    public static String doGet(String url){
        return doGet(url,null);
    }

    /**
     * 带参数的 POST请求
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url,Map<String,String> param){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        //创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> paramList = new ArrayList<>();
        if(Objects.nonNull(param)) {
            for (String key : param.keySet()) {
                paramList.add(new BasicNameValuePair(key, param.get(key)));
            }
        }
            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(urlEncodedFormEntity);
                //执行http请求
                response = httpClient.execute(httpPost);
                resultString = EntityUtils.toString(response.getEntity(), "Utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return resultString;
    }

    /**
     * 不带参数的POST请求
     * @param url
     * @return
     */
    public static String doPost(String url){
        return doPost(url,null);
    }


    /**
     * 根据url下载文件，保存到filepath中
     * @param url
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public static File download(String url,String prefix,String suffix) {
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig config = null;
        File file = null;
        //使用代理
        //目标文件url
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        //下载需登陆，设置登陆后的cookie
        httpGet.setHeader("Cookie","JSESSIONID=FF8A38A86462286FFC4DE4A28ECAA9F4");
        try {
            HttpResponse respone = client.execute(httpGet);
            HttpEntity entity = respone.getEntity();
            if (entity != null) {
                InputStream is = entity.getContent();
                file = File.createTempFile(prefix+"-", "."+suffix);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            logger.error(("loadProperties IOException:" + e.getMessage()));
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}