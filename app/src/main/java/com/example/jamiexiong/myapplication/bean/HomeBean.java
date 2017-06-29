package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/6/19.
 */

public class HomeBean {

    /**
     * code : 200
     * result : [{"AlertSetID":"0","AlarmMachine":"DL-102","AlarmContent":"1车间的发出维修申请","AlarmStatus":"1","SendFlag":"1","ActualValue":"","AlarmTime":"2017-06-05 19:43:03","AlarmFlag":"1"}]
     * statue : success
     */

    private String code;
    private String statue;
    private List<HomeResult> result;

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

    public List<HomeResult> getResult() {
        return result;
    }

    public void setResult(List<HomeResult> result) {
        this.result = result;
    }

    public static class HomeResult {
        /**
         * AlertSetID : 0
         * AlarmMachine : DL-102
         * AlarmContent : 1车间的发出维修申请
         * AlarmStatus : 1
         * SendFlag : 1
         * ActualValue :
         * AlarmTime : 2017-06-05 19:43:03
         * AlarmFlag : 1
         */

        private String AlertSetID;
        private String AlarmMachine;
        private String AlarmContent;
        private String AlarmStatus;
        private String SendFlag;
        private String ActualValue;
        private String AlarmTime;
        private String AlarmFlag;

        public String getAlertSetID() {
            return AlertSetID;
        }

        public void setAlertSetID(String AlertSetID) {
            this.AlertSetID = AlertSetID;
        }

        public String getAlarmMachine() {
            return AlarmMachine;
        }

        public void setAlarmMachine(String AlarmMachine) {
            this.AlarmMachine = AlarmMachine;
        }

        public String getAlarmContent() {
            return AlarmContent;
        }

        public void setAlarmContent(String AlarmContent) {
            this.AlarmContent = AlarmContent;
        }

        public String getAlarmStatus() {
            return AlarmStatus;
        }

        public void setAlarmStatus(String AlarmStatus) {
            this.AlarmStatus = AlarmStatus;
        }

        public String getSendFlag() {
            return SendFlag;
        }

        public void setSendFlag(String SendFlag) {
            this.SendFlag = SendFlag;
        }

        public String getActualValue() {
            return ActualValue;
        }

        public void setActualValue(String ActualValue) {
            this.ActualValue = ActualValue;
        }

        public String getAlarmTime() {
            return AlarmTime;
        }

        public void setAlarmTime(String AlarmTime) {
            this.AlarmTime = AlarmTime;
        }

        public String getAlarmFlag() {
            return AlarmFlag;
        }

        public void setAlarmFlag(String AlarmFlag) {
            this.AlarmFlag = AlarmFlag;
        }
    }
}
