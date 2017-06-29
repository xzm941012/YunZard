package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/28.
 */

public class FocusListBean {

    /**
     * code : 200
     * result : [{"id":"1","codes":"BZ-101","name":"自动成圈机","status":"offline","attentionType":"1"},{"id":"2301","codes":"JS-101","name":"70+35塑料挤出机","status":"online","attentionType":"1"},{"id":"2300","codes":"ZL-201","name":"中拉机-1号","status":"offline","attentionType":"1"},{"id":"2293","codes":"RB-101","name":"ZH-650高速绕包机1","status":"offline","attentionType":"1"}]
     * statue : success
     */

    private String code;
    private String statue;
    private List<FoocusListItems> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public List<FoocusListItems> getResult() {
        return result;
    }

    public void setResult(List<FoocusListItems> result) {
        this.result = result;
    }

    public static class FoocusListItems {
        /**
         * id : 1
         * codes : BZ-101
         * name : 自动成圈机
         * status : offline
         * attentionType : 1
         */

        private String id;
        private String codes;
        private String name;
        private String status;
        private String attentionType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCodes() {
            return codes;
        }

        public void setCodes(String codes) {
            this.codes = codes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAttentionType() {
            return attentionType;
        }

        public void setAttentionType(String attentionType) {
            this.attentionType = attentionType;
        }
    }
}
