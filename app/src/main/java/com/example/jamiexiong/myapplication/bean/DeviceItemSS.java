package com.example.jamiexiong.myapplication.bean;

import java.util.List;

/**
 * Created by jamiexiong on 2017/9/4.
 */

public class DeviceItemSS {

    /**
     * result : [{"operationData":"0--mm/min","timeDiff":"57","gatherTime":"2017-09-04 14:09:21","reg_id":"7485","operationValue":"进给速度","id":"7485"},{"operationData":"181.399994","timeDiff":"56","gatherTime":"2017-09-04 14:09:08","reg_id":"7486","operationValue":"控制轴相对坐标3","id":"7486"},{"operationData":"546.869995","timeDiff":"56","gatherTime":"2017-09-04 14:09:08","reg_id":"7487","operationValue":"控制轴绝对坐标3","id":"7487"},{"operationData":"600.000000","timeDiff":"56","gatherTime":"2017-09-04 14:09:08","reg_id":"7488","operationValue":"控制轴机器坐标3","id":"7488"},{"operationData":"0.000000","timeDiff":"56","gatherTime":"2017-09-04 14:09:13","reg_id":"7489","operationValue":"控制轴剩余移动量3","id":"7489"},{"operationData":"-83.000000","timeDiff":"42","gatherTime":"2017-09-04 13:54:58","reg_id":"7490","operationValue":"控制轴跳过位置3","id":"7490"},{"operationData":"0.000000","timeDiff":"42","gatherTime":"2017-09-04 13:54:58","reg_id":"7491","operationValue":"伺服轴延迟3","id":"7491"},{"operationData":"0","timeDiff":"42","gatherTime":"2017-09-04 13:54:58","reg_id":"7492","operationValue":"伺服闭环增益3","id":"7492"},{"operationData":"0--rpm","timeDiff":"42","gatherTime":"2017-09-04 13:54:58","reg_id":"7493","operationValue":"主轴转速","id":"7493"},{"operationData":"0.00--percent","timeDiff":"42","gatherTime":"2017-09-04 13:54:58","reg_id":"7494","operationValue":"主轴负载","id":"7494"},{"operationData":"29.16","timeDiff":"114","gatherTime":"2017-09-04 15:06:52","reg_id":"7514","operationValue":"温度","id":"7514"},{"operationData":"50.24","timeDiff":"116","gatherTime":"2017-09-04 15:08:56","reg_id":"7515","operationValue":"湿度","id":"7515"}]
     * code : 200
     * status : success
     */

    private int code;
    private String status;
    private List<DeviceItemSSItem> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DeviceItemSSItem> getResult() {
        return result;
    }

    public void setResult(List<DeviceItemSSItem> result) {
        this.result = result;
    }

    public static class DeviceItemSSItem {
        /**
         * operationData : 0--mm/min
         * timeDiff : 57
         * gatherTime : 2017-09-04 14:09:21
         * reg_id : 7485
         * operationValue : 进给速度
         * id : 7485
         */

        private String operationData;
        private String timeDiff;
        private String gatherTime;
        private String reg_id;
        private String operationValue;
        private String id;

        public String getOperationData() {
            return operationData;
        }

        public void setOperationData(String operationData) {
            this.operationData = operationData;
        }

        public String getTimeDiff() {
            return timeDiff;
        }

        public void setTimeDiff(String timeDiff) {
            this.timeDiff = timeDiff;
        }

        public String getGatherTime() {
            return gatherTime;
        }

        public void setGatherTime(String gatherTime) {
            this.gatherTime = gatherTime;
        }

        public String getReg_id() {
            return reg_id;
        }

        public void setReg_id(String reg_id) {
            this.reg_id = reg_id;
        }

        public String getOperationValue() {
            return operationValue;
        }

        public void setOperationValue(String operationValue) {
            this.operationValue = operationValue;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
