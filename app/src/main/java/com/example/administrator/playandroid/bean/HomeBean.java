package com.example.administrator.playandroid.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class HomeBean {
    public String reason;
    public ResultHome result;
    public int error_code;
    public  class ResultHome{
        public String stat;
        public List<DataHome> data;
    }
    public  class DataHome{
        public String uniquekey;
        public String title;
        public String date;
        public String category;
        public String author_name;
        public String url;
        public String thumbnail_pic_s;
        public String thumbnail_pic_s02;
        public String thumbnail_pic_s03;

    }
}
