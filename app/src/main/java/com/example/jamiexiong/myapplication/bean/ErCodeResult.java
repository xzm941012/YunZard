package com.example.jamiexiong.myapplication.bean;

/**
 * Created by jamiexiong on 2017/6/8.
 */

public class ErCodeResult {

    /**
     * code : 200
     * result : {"id":"have"}
     * statue : success
     */

    private String code;
    private ResultBean result;
    private String statue;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public static class ResultBean {
        /**
         * id : have
         */

        private String id;

        private String onlineCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOnlineCode() {
            return onlineCode;
        }

        public void setOnlineCode(String onlineCode) {
            this.onlineCode = onlineCode;
        }
    }
}
