package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;
import java.util.List;

public class SobotProvinInfo implements Serializable {
    List<SobotProvinceModel> areas;
    List<SobotProvinceModel> citys;
    List<SobotProvinceModel> provinces;

    public List<SobotProvinceModel> getProvinces() {
        return this.provinces;
    }

    public void setProvinces(List<SobotProvinceModel> list) {
        this.provinces = list;
    }

    public List<SobotProvinceModel> getCitys() {
        return this.citys;
    }

    public void setCitys(List<SobotProvinceModel> list) {
        this.citys = list;
    }

    public List<SobotProvinceModel> getAreas() {
        return this.areas;
    }

    public void setAreas(List<SobotProvinceModel> list) {
        this.areas = list;
    }

    public static class SobotProvinceModel implements Serializable {
        public String areaId;
        public String areaName;
        public String cityId;
        public String cityName;
        public boolean isChecked = false;
        public int level = 0;
        public boolean nodeFlag;
        public String provinceId;
        public String provinceName;

        @Override // java.lang.Object
        public String toString() {
            return "SobotProvinceModel{provinceId='" + this.provinceId + DateFormat.QUOTE + ", provinceName='" + this.provinceName + DateFormat.QUOTE + ", cityId='" + this.cityId + DateFormat.QUOTE + ", cityName='" + this.cityName + DateFormat.QUOTE + ", areaId='" + this.areaId + DateFormat.QUOTE + ", areaName='" + this.areaName + DateFormat.QUOTE + ", level=" + this.level + ", isChecked=" + this.isChecked + '}';
        }
    }
}
