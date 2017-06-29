package com.example.jamiexiong.myapplication.bean;

/**
 * Created by jamiexiong on 2017/6/7.
 */

public class ResultCode {

    /**
     * result : {"id":"999","token":""}
     * code : 200
     */

    private ResultBean result;
    private String code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class ResultBean {
        /**
         * id : 999
         * token :
         */

        private String id;
        private String token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
