package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/12.
 */

public class DeviceItemBean {

    /**
     * code : 200
     * result : [{"ztName":"1车间","id":"2300","machName":"中拉机-1号","code":"ZL-201","status":"offline","DiffData":"0.00","outs":"0.00","machAttention":"0"},{"ztName":"1车间","id":"2294","machName":"铜大拉连续退火机组-2","code":"DL-102","status":"online","DiffData":"0.00","outs":"0.00","machAttention":"0"},{"ztName":"1车间","id":"2295","machName":"铜大拉连续退火机组-1","code":"DL-101","status":"offline","DiffData":"0.00","outs":"0.00","machAttention":"0"}]
     * statue : success
     */

    private String code;
    private String statue;
    private List<DeviceResultBean> result;

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

    public List<DeviceResultBean> getResult() {
        return result;
    }

    public void setResult(List<DeviceResultBean> result) {
        this.result = result;
    }

    public static class DeviceResultBean {
        /**
         * ztName : 1车间
         * id : 2300
         * machName : 中拉机-1号
         * code : ZL-201
         * status : offline
         * DiffData : 0.00
         * outs : 0.00
         * machAttention : 0
         */

        private String ztName;
        private String id;
        private String machName;
        private String code;
        private String status;
        private String DiffData;
        private String outs;
        private String machAttention;

        public String getZtName() {
            return ztName;
        }

        public void setZtName(String ztName) {
            this.ztName = ztName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMachName() {
            return machName;
        }

        public void setMachName(String machName) {
            this.machName = machName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDiffData() {
            return DiffData;
        }

        public void setDiffData(String DiffData) {
            this.DiffData = DiffData;
        }

        public String getOuts() {
            return outs;
        }

        public void setOuts(String outs) {
            this.outs = outs;
        }

        public String getMachAttention() {
            return machAttention;
        }

        public void setMachAttention(String machAttention) {
            this.machAttention = machAttention;
        }

        @Override
        public String toString() {
            return machName;
        }
    }
}
