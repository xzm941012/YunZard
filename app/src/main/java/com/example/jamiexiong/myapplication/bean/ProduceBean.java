package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/9.
 */

public class ProduceBean {

    /**
     * code : 200
     * result : [{"POCode":"1706010001_0002_00100052","State":"已完成","Qty":"4.6","dPlanSTime":"2017-06-12","dPlanETime":"2017-06-13","cProcedureName":"绕包","SalesOrder":"00009834","ztName":"2车间","cMac":"RB-102"},{"POCode":"1706010001_0002_00100053","State":"已完成","Qty":"4.888","dPlanSTime":"2017-06-12","dPlanETime":"2017-06-13","cProcedureName":"绕包","SalesOrder":"00009834","ztName":"2车间","cMac":"RB-101"},{"POCode":"1706010001_0002_00100054","State":"已完成","Qty":"1.374","dPlanSTime":"2017-06-13","dPlanETime":"2017-06-14","cProcedureName":"绕包","SalesOrder":"00009834","ztName":"2车间","cMac":"RB-102"},{"POCode":"1706010001_0002_00100055","State":"已完成","Qty":"4.888","dPlanSTime":"2017-06-13","dPlanETime":"2017-06-14","cProcedureName":"绕包","SalesOrder":"00009834","ztName":"2车间","cMac":"RB-101"},{"POCode":"1706010001_0002_00200027","State":"已完成","Qty":"12.749","dPlanSTime":"2017-06-14","dPlanETime":"2017-06-15","cProcedureName":"绝缘+成盘","SalesOrder":"00009834","ztName":"2车间","cMac":"JS-101"},{"POCode":"1706010001_0002_00200028","State":"已完成","Qty":"2.843","dPlanSTime":"2017-06-15","dPlanETime":"2017-06-16","cProcedureName":"绝缘+成盘","SalesOrder":"00009834","ztName":"2车间","cMac":"JS-101"}]
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
         * POCode : 1706010001_0002_00100052
         * State : 已完成
         * Qty : 4.6
         * dPlanSTime : 2017-06-12
         * dPlanETime : 2017-06-13
         * cProcedureName : 绕包
         * SalesOrder : 00009834
         * ztName : 2车间
         * cMac : RB-102
         */

        private String POCode;
        private String State;
        private String Qty;
        private String dPlanSTime;
        private String dPlanETime;
        private String cProcedureName;
        private String SalesOrder;
        private String ztName;
        private String cMac;

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

        public String getQty() {
            return Qty;
        }

        public void setQty(String Qty) {
            this.Qty = Qty;
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

        public String getSalesOrder() {
            return SalesOrder;
        }

        public void setSalesOrder(String SalesOrder) {
            this.SalesOrder = SalesOrder;
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

        @Override
        public String toString() {
            return "ResultBean{" +
                    "POCode='" + POCode + '\'' +
                    ", State='" + State + '\'' +
                    ", Qty='" + Qty + '\'' +
                    ", dPlanSTime='" + dPlanSTime + '\'' +
                    ", dPlanETime='" + dPlanETime + '\'' +
                    ", cProcedureName='" + cProcedureName + '\'' +
                    ", SalesOrder='" + SalesOrder + '\'' +
                    ", ztName='" + ztName + '\'' +
                    ", cMac='" + cMac + '\'' +
                    '}';
        }
    }
}
