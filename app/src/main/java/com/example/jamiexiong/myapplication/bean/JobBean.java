package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/8.
 */

public class JobBean {

    /**
     * code : 200
     * result : [{"value":"2","group":"互联网/信息技术","text":"计算机软件"},{"value":"3","group":"互联网/信息技术","text":"硬件设施服务"}]
     * statue : success
     */

    private int code;
    private String statue;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * value : 2
         * group : 互联网/信息技术
         * text : 计算机软件
         */

        private String value;
        private String group;
        private String text;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
