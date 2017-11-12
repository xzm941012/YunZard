package com.example.jamiexiong.myapplication.bean;

/**
 * Created by jamiexiong on 2017/6/7.
 */

public class ResultCode {


    /**
     * code : 200
     * result : {"id":"1137","phoneNum":"18816404955","name":"18816404955","token":"","nickname":"Jamie Xiong"}
     */

    private String code;
    private ResultBean result;

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

    public static class ResultBean {
        /**
         * id : 1137
         * phoneNum : 18816404955
         * name : 18816404955
         * token :
         * nickname : Jamie Xiong
         */

        private String id;
        private String phoneNum;
        private String name;
        private String token;
        private String nickname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
