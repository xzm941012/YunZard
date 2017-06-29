package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/8.
 */

public class GroupBean {

    /**
     * code : 200
     * result : [{"id":"1","typeName":"互联网/信息技术"},{"id":"4","typeName":"制造业"},{"id":"7","typeName":"贸易/批发/零售"},{"id":"10","typeName":"房地产业"},{"id":"13","typeName":"建筑业"},{"id":"16","typeName":"金融业"},{"id":"19","typeName":"服务业"},{"id":"22","typeName":"运输/物流/仓储"},{"id":"25","typeName":"教育行业"},{"id":"28","typeName":"文体/娱乐/传媒"},{"id":"31","typeName":"商业服务/租赁"},{"id":"34","typeName":"医疗医药"},{"id":"37","typeName":"政府/事业单位"},{"id":"40","typeName":"社会组织"},{"id":"43","typeName":"科研服务"},{"id":"46","typeName":"公共/环境"},{"id":"49","typeName":"居民服务"},{"id":"52","typeName":"开采业"},{"id":"55","typeName":"农/林/牧/渔"},{"id":"58","typeName":"电/热/燃气/水供应"},{"id":"61","typeName":"亲朋好友"}]
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
         * id : 1
         * typeName : 互联网/信息技术
         */

        private String id;
        private String typeName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
