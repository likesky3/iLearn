package org.work.sogou.searchhub;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonParseExample {

    private static final ArrayList<String> IMAGE_META_ATTRIBUTES = new ArrayList<String>();
    static {
        IMAGE_META_ATTRIBUTES.add("state");
        IMAGE_META_ATTRIBUTES.add("thumb_width"); 
//        IMAGE_META_ATTRIBUTES.add("thumb_height"); 
//        IMAGE_META_ATTRIBUTES.add("flag"); 
//        IMAGE_META_ATTRIBUTES.add("r_width"); 
//        IMAGE_META_ATTRIBUTES.add("r_height"); 
//        IMAGE_META_ATTRIBUTES.add("r_x"); 
//        IMAGE_META_ATTRIBUTES.add("r_y"); 
//        IMAGE_META_ATTRIBUTES.add("img_width"); 
//        IMAGE_META_ATTRIBUTES.add("img_height");
    }
    
    public ArrayList<ArrayList<String>> parseJson(String jsonString) throws JSONException {
        ArrayList<ArrayList<String>> result = null;
        JSONObject jo = new JSONObject(jsonString);
        System.out.println("input:" + jsonString);
        System.out.println();
        try {
//          System.out.println("code=" + jo.get("code"));
//            JSONArray jsonArray = jo.getJSONArray("comments");
            JSONArray jsonArray = jo.getJSONArray("metaArray");
            if (jsonArray != null) {
                int metaArrayItemNum = jsonArray.length();
                int imageMetaAttrNum = IMAGE_META_ATTRIBUTES.size();
                result = new ArrayList<ArrayList<String>>(metaArrayItemNum);
                System.out.println("num=" + metaArrayItemNum);
                for (int i = 0; i < metaArrayItemNum; i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    ArrayList<String> imageMeta = new ArrayList<String>();
                    for (int j = 0; j < imageMetaAttrNum; j++) {
                        String value = item.getString(IMAGE_META_ATTRIBUTES.get(j));
                        imageMeta.add(value);
                        System.out.println(value);
                    }
                }
            }
//          String[] names = JSONObject.getNames(jo);
//          for(String name: names) {
//              System.out.println(name + " " + jo.get(name));
//              
//          }
            
        } catch (JSONException e) {
            System.out.println("parse json throw exception: " + e.getMessage());
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        JsonParseExample instance = new JsonParseExample();
        StringBuilder sb = new StringBuilder();
        try {
            // 开始从文件中读一直报没有metaArray这个对象，后发现文件中的数据双引号是中文的，改成英文格式即ok
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/jsonData"), "utf8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
//                ArrayList<ArrayList<String>> result = instance.parseJson("{\"code\":ok, \"metaArray\":[{\"state\":\"abc\", \"thumb_width\":\"def\"}]}");
                ArrayList<ArrayList<String>> result2 = instance.parseJson("{\"metaArray\":[{\"state\":\"abc\", \"thumb_width\":\"def\"}]}");
            ArrayList<ArrayList<String>> result = instance.parseJson(line);
                for (ArrayList<String> item : result) {
                    for (String data: item) {
                        System.out.println(data);
                    }
                }
            }
                
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
