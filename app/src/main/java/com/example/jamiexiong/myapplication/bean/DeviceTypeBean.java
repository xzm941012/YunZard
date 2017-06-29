package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/12.
 */

public class DeviceTypeBean {

    /**
     * code : 200
     * result : [{"id":"-1","ztName":"我的关注"},{"id":"4","ztName":"工厂管家"},{"id":"127","ztName":"1车间"},{"id":"265","ztName":"2车间"}]
     * statue : success
     */

    private String code;
    private String statue;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : -1
         * ztName : 我的关注
         */

        private String id;
        private String ztName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZtName() {
            return ztName;
        }

        public void setZtName(String ztName) {
            this.ztName = ztName;
        }

        @Override
        public String toString() {
            return ztName;
        }
    }
}
