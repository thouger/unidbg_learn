package cn.missfresh.module.base.bean;

import java.util.List;

public class TencentPoiData {
    private List<DataBean> data;
    private RegionBean region;

    public RegionBean getRegion() {
        return this.region;
    }

    public void setRegion(RegionBean regionBean) {
        this.region = regionBean;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public static class RegionBean {
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }

    public static class DataBean {
        private double _distance;
        private AdInfoBean ad_info;
        private String address;
        private String category;
        private String id;
        private LocationBean location;
        private PanoBean pano;
        private String tel;
        private String title;
        private int type;

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public String getTel() {
            return this.tel;
        }

        public void setTel(String str) {
            this.tel = str;
        }

        public String getCategory() {
            return this.category;
        }

        public void setCategory(String str) {
            this.category = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public LocationBean getLocation() {
            return this.location;
        }

        public void setLocation(LocationBean locationBean) {
            this.location = locationBean;
        }

        public double get_distance() {
            return this._distance;
        }

        public void set_distance(double d) {
            this._distance = d;
        }

        public PanoBean getPano() {
            return this.pano;
        }

        public void setPano(PanoBean panoBean) {
            this.pano = panoBean;
        }

        public AdInfoBean getAd_info() {
            return this.ad_info;
        }

        public void setAd_info(AdInfoBean adInfoBean) {
            this.ad_info = adInfoBean;
        }

        public static class LocationBean {
            private double lat;
            private double lng;

            public double getLat() {
                return this.lat;
            }

            public void setLat(double d) {
                this.lat = d;
            }

            public double getLng() {
                return this.lng;
            }

            public void setLng(double d) {
                this.lng = d;
            }
        }

        public static class PanoBean {
            private Object heading;
            private String id;
            private Object pitch;
            private Object zoom;

            public String getId() {
                return this.id;
            }

            public void setId(String str) {
                this.id = str;
            }

            public Object getHeading() {
                return this.heading;
            }

            public void setHeading(Object obj) {
                this.heading = obj;
            }

            public Object getPitch() {
                return this.pitch;
            }

            public void setPitch(Object obj) {
                this.pitch = obj;
            }

            public Object getZoom() {
                return this.zoom;
            }

            public void setZoom(Object obj) {
                this.zoom = obj;
            }
        }

        public static class AdInfoBean {
            private int adcode;
            private String city;
            private String district;
            private String province;

            public int getAdcode() {
                return this.adcode;
            }

            public void setAdcode(int i) {
                this.adcode = i;
            }

            public String getProvince() {
                return this.province;
            }

            public void setProvince(String str) {
                this.province = str;
            }

            public String getCity() {
                return this.city;
            }

            public void setCity(String str) {
                this.city = str;
            }

            public String getDistrict() {
                return this.district;
            }

            public void setDistrict(String str) {
                this.district = str;
            }
        }
    }
}
