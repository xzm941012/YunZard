package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/13.
 */

public class ProduceDetailBean {

    /**
     * code : 200
     * result : [{"POCode":"1706010001_0002_00100052","State":"已完成","cLinePsn":"","dPlanSTime":"2017-06-12","dPlanETime":"2017-06-13","cProcedureName":"绕包","ztName":"2车间","cMac":"RB-102","cInvName":"布电线","spec":"NHBV","tooth":"红","materialQuality":"NDT","electroplateType":"d","SalesOrder":"00009834","Qty":"4.6","buckets":"1"}]
     * statue : success
     */

    private String code;
    private String statue;
    private List<ProduceResultBean> result;

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

    public List<ProduceResultBean> getResult() {
        return result;
    }

    public void setResult(List<ProduceResultBean> result) {
        this.result = result;
    }

    public static class ProduceResultBean {
        /**
         * POCode : 1706010001_0002_00100052
         * State : 已完成
         * cLinePsn :
         * dPlanSTime : 2017-06-12
         * dPlanETime : 2017-06-13
         * cProcedureName : 绕包
         * ztName : 2车间
         * cMac : RB-102
         * cInvName : 布电线
         * spec : NHBV
         * tooth : 红
         * materialQuality : NDT
         * electroplateType : d
         * SalesOrder : 00009834
         * Qty : 4.6
         * buckets : 1
         */

        private String POCode;
        private String State;
        private String cLinePsn;
        private String dPlanSTime;
        private String dPlanETime;
        private String cProcedureName;
        private String ztName;
        private String cMac;
        private String cInvName;
        private String spec;
        private String tooth;
        private String materialQuality;
        private String electroplateType;
        private String SalesOrder;
        private String Qty;
        private String buckets;

        public String getPOCode() {
            return POCode;
        }

        public void setPOCode(String POCode) {
            this.POCode = POCode;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getCLinePsn() {
            return cLinePsn;
        }

        public void setCLinePsn(String cLinePsn) {
            this.cLinePsn = cLinePsn;
        }

        public String getDPlanSTime() {
            return dPlanSTime;
        }

        public void setDPlanSTime(String dPlanSTime) {
            this.dPlanSTime = dPlanSTime;
        }

        public String getDPlanETime() {
            return dPlanETime;
        }

        public void setDPlanETime(String dPlanETime) {
            this.dPlanETime = dPlanETime;
        }

        public String getCProcedureName() {
            return cProcedureName;
        }

        public void setCProcedureName(String cProcedureName) {
            this.cProcedureName = cProcedureName;
        }

        public String getZtName() {
            return ztName;
        }

        public void setZtName(String ztName) {
            this.ztName = ztName;
        }

        public String getCMac() {
            return cMac;
        }

        public void setCMac(String cMac) {
            this.cMac = cMac;
        }

        public String getCInvName() {
            return cInvName;
        }

        public void setCInvName(String cInvName) {
            this.cInvName = cInvName;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getTooth() {
            return tooth;
        }

        public void setTooth(String tooth) {
            this.tooth = tooth;
        }

        public String getMaterialQuality() {
            return materialQuality;
        }

        public void setMaterialQuality(String materialQuality) {
            this.materialQuality = materialQuality;
        }

        public String getElectroplateType() {
            return electroplateType;
        }

        public void setElectroplateType(String electroplateType) {
            this.electroplateType = electroplateType;
        }

        public String getSalesOrder() {
            return SalesOrder;
        }

        public void setSalesOrder(String SalesOrder) {
            this.SalesOrder = SalesOrder;
        }

        public String getQty() {
            return Qty;
        }

        public void setQty(String Qty) {
            this.Qty = Qty;
        }

        public String getBuckets() {
            return buckets;
        }

        public void setBuckets(String buckets) {
            this.buckets = buckets;
        }
    }
}
