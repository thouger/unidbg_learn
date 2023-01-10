package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectSkuProductBean {
    private List<AttributesEntity> attributes = new ArrayList();
    public String defaultSelectedSku = "";
    private Map<String, BaseSkuBean> productStocks = new HashMap();

    public SelectSkuProductBean() {
        AppMethodBeat.i(6939, false);
        AppMethodBeat.o(6939);
    }

    public Map<String, BaseSkuBean> getProductStocks() {
        return this.productStocks;
    }

    public void setProductStocks(Map<String, BaseSkuBean> map) {
        this.productStocks = map;
    }

    public List<AttributesEntity> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<AttributesEntity> list) {
        this.attributes = list;
    }

    public static class AttributesEntity {
        private List<AttributeMembersEntity> attributeMembers = new ArrayList();
        private int id;
        private String name;

        public AttributesEntity() {
            AppMethodBeat.i(6927, false);
            AppMethodBeat.o(6927);
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public List<AttributeMembersEntity> getAttributeMembers() {
            return this.attributeMembers;
        }

        public void setAttributeMembers(List<AttributeMembersEntity> list) {
            this.attributeMembers = list;
        }

        public static class AttributeMembersEntity {
            private int attributeGroupId;
            private int attributeMemberId;
            private String name;
            private int status;

            public AttributeMembersEntity(int i, int i2, String str) {
                this.attributeGroupId = i;
                this.attributeMemberId = i2;
                this.name = str;
            }

            public int hashCode() {
                int i = 0;
                AppMethodBeat.i(6908, false);
                String str = this.name;
                if (str != null) {
                    i = str.hashCode();
                }
                int i2 = ((((MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + i) * 31) + this.attributeGroupId) * 31) + this.attributeMemberId;
                AppMethodBeat.o(6908);
                return i2;
            }

            public boolean equals(Object obj) {
                boolean z = false;
                AppMethodBeat.i(6910, false);
                if (!(obj instanceof AttributeMembersEntity)) {
                    AppMethodBeat.o(6910);
                    return false;
                }
                AttributeMembersEntity attributeMembersEntity = (AttributeMembersEntity) obj;
                if (attributeMembersEntity.getName().equals(this.name) && attributeMembersEntity.getAttributeGroupId() == this.attributeGroupId && attributeMembersEntity.getAttributeMemberId() == this.attributeMemberId) {
                    z = true;
                }
                AppMethodBeat.o(6910);
                return z;
            }

            public int getStatus() {
                return this.status;
            }

            public void setStatus(int i) {
                this.status = i;
            }

            public int getAttributeGroupId() {
                return this.attributeGroupId;
            }

            public void setAttributeGroupId(int i) {
                this.attributeGroupId = i;
            }

            public int getAttributeMemberId() {
                return this.attributeMemberId;
            }

            public void setAttributeMemberId(int i) {
                this.attributeMemberId = i;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }
        }
    }
}
