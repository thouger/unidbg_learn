package cn.missfresh.module.base.support.modelsupport.event.bean;

import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import java.util.List;

public class ProductBeans {

    public static class PreSaleListItem {
        private int event_active;
        private String image;
        private int img_type;
        private String name;
        private String ordering;
        private String sku;
        private String subtitle;
        private int vip_price;

        public interface IImgType {
            public static final int image = 0;
            public static final int video = 1;
        }

        public String getSku() {
            return this.sku;
        }

        public void setSku(String str) {
            this.sku = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getSubtitle() {
            return this.subtitle;
        }

        public void setSubtitle(String str) {
            this.subtitle = str;
        }

        public String getOrdering() {
            return this.ordering;
        }

        public void setOrdering(String str) {
            this.ordering = str;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public int getVip_price() {
            return this.vip_price;
        }

        public void setVip_price(int i) {
            this.vip_price = i;
        }

        public int getEvent_active() {
            return this.event_active;
        }

        public void setEvent_active(int i) {
            this.event_active = i;
        }

        public int getImg_type() {
            return this.img_type;
        }

        public void setImg_type(int i) {
            this.img_type = i;
        }

        public String toString() {
            AppMethodBeat.i(22172, false);
            String jSONString = JSONObject.toJSONString(this);
            AppMethodBeat.o(22172);
            return jSONString;
        }
    }

    public static class GetPreSaleListRes {
        private int page_no;
        private int pages;
        private List<PreSaleListItem> products;

        public int getPage_no() {
            return this.page_no;
        }

        public void setPage_no(int i) {
            this.page_no = i;
        }

        public int getPages() {
            return this.pages;
        }

        public void setPages(int i) {
            this.pages = i;
        }

        public List<PreSaleListItem> getProducts() {
            return this.products;
        }

        public void setProducts(List<PreSaleListItem> list) {
            this.products = list;
        }
    }

    public static class Instruction {
        private String blur_image;
        private String description;
        private String image;
        private int ordering;
        private String title;
        private String video;

        public String getBlur_image() {
            return this.blur_image;
        }

        public void setBlur_image(String str) {
            this.blur_image = str;
        }

        public int getOrdering() {
            return this.ordering;
        }

        public void setOrdering(int i) {
            this.ordering = i;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public String getVideo() {
            return this.video;
        }

        public void setVideo(String str) {
            this.video = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String toString() {
            AppMethodBeat.i(22133, false);
            String jSONString = JSONObject.toJSONString(this);
            AppMethodBeat.o(22133);
            return jSONString;
        }
    }

    public static class PreSaleProductDetail {
        private String deliver_time;
        private int event_active;
        private String event_internal_id;
        private List<Instruction> instruction;
        private ShareInfo share_info;
        private int vip_price;

        public int getVip_price() {
            return this.vip_price;
        }

        public void setVip_price(int i) {
            this.vip_price = i;
        }

        public String getDeliver_time() {
            return this.deliver_time;
        }

        public void setDeliver_time(String str) {
            this.deliver_time = str;
        }

        public List<Instruction> getInstruction() {
            return this.instruction;
        }

        public void setInstruction(List<Instruction> list) {
            this.instruction = list;
        }

        public int getEvent_active() {
            return this.event_active;
        }

        public void setEvent_active(int i) {
            this.event_active = i;
        }

        public String getEvent_internal_id() {
            return this.event_internal_id;
        }

        public void setEvent_internal_id(String str) {
            this.event_internal_id = str;
        }

        public ShareInfo getShare_info() {
            return this.share_info;
        }

        public void setShare_info(ShareInfo shareInfo) {
            this.share_info = shareInfo;
        }

        public String toString() {
            AppMethodBeat.i(22174, false);
            String jSONString = JSONObject.toJSONString(this);
            AppMethodBeat.o(22174);
            return jSONString;
        }
    }
}
