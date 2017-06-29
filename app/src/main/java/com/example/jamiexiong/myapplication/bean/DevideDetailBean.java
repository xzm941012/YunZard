package com.example.jamiexiong.myapplication.bean;

/**
 * Created by jamiexiong on 2017/6/12.
 */

public class DevideDetailBean {

    /**
     * code : 200
     * result : {"maintenanceperiod":"90.0","maintenanceday":"2017-05-12","maintenancetype":"B:二级保养","repairday":"--","person":"--","personnumber":"--","repairreason":"/","maintanceCode":"317","updateTime":"2017-05-23 17:42:46"}
     * statue : success
     */

    private int code;
    private DetailResultBean result;
    private String statue;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DetailResultBean getResult() {
        return result;
    }

    public void setResult(DetailResultBean result) {
        this.result = result;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public static class DetailResultBean {
        /**
         * maintenanceperiod : 90.0
         * maintenanceday : 2017-05-12
         * maintenancetype : B:二级保养
         * repairday : --
         * person : --
         * personnumber : --
         * repairreason : /
         * maintanceCode : 317
         * updateTime : 2017-05-23 17:42:46
         */

        private String maintenanceperiod;
        private String maintenanceday;
        private String maintenancetype;
        private String repairday;
        private String person;
        private String personnumber;
        private String repairreason;
        private String maintanceCode;
        private String updateTime;

        public String getMaintenanceperiod() {
            return maintenanceperiod;
        }

        public void setMaintenanceperiod(String maintenanceperiod) {
            this.maintenanceperiod = maintenanceperiod;
        }

        public String getMaintenanceday() {
            return maintenanceday;
        }

        public void setMaintenanceday(String maintenanceday) {
            this.maintenanceday = maintenanceday;
        }

        public String getMaintenancetype() {
            return maintenancetype;
        }

        public void setMaintenancetype(String maintenancetype) {
            this.maintenancetype = maintenancetype;
        }

        public String getRepairday() {
            return repairday;
        }

        public void setRepairday(String repairday) {
            this.repairday = repairday;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
        }

        public String getPersonnumber() {
            return personnumber;
        }

        public void setPersonnumber(String personnumber) {
            this.personnumber = personnumber;
        }

        public String getRepairreason() {
            return repairreason;
        }

        public void setRepairreason(String repairreason) {
            this.repairreason = repairreason;
        }

        public String getMaintanceCode() {
            return maintanceCode;
        }

        public void setMaintanceCode(String maintanceCode) {
            this.maintanceCode = maintanceCode;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
