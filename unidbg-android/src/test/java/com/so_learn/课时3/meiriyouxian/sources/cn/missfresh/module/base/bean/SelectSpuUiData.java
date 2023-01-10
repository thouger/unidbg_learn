package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.bean.SelectSkuProductBean;
import cn.missfresh.module.base.bean.SpuParams;
import cn.missfresh.module.base.common.adapter.SelectSkuAdapter;
import cn.missfresh.module.base.common.adapter.SelectSpuServiceAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectSpuUiData {
    List<SelectSkuAdapter> adapters = new ArrayList();
    Map<String, BaseSkuBean> result;
    HashMap<String, SelectSpuServiceAdapter> selectServiceAdapters = new HashMap<>();
    List<SelectSkuProductBean.AttributesEntity.AttributeMembersEntity> selectedEntities = new ArrayList();
    HashMap<String, SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX> serviceResult = new HashMap<>();

    public SelectSpuUiData() {
        AppMethodBeat.i(6957, false);
        AppMethodBeat.o(6957);
    }

    public HashMap<String, SelectSpuServiceAdapter> getSelectServiceAdapters() {
        return this.selectServiceAdapters;
    }

    public void setSelectServiceAdapters(HashMap<String, SelectSpuServiceAdapter> hashMap) {
        this.selectServiceAdapters = hashMap;
    }

    public HashMap<String, SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX> getServiceResult() {
        return this.serviceResult;
    }

    public void setServiceResult(HashMap<String, SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX> hashMap) {
        this.serviceResult = hashMap;
    }

    public List<SelectSkuAdapter> getAdapters() {
        return this.adapters;
    }

    public void setAdapters(List<SelectSkuAdapter> list) {
        this.adapters = list;
    }

    public Map<String, BaseSkuBean> getResult() {
        return this.result;
    }

    public void setResult(Map<String, BaseSkuBean> map) {
        this.result = map;
    }

    public List<SelectSkuProductBean.AttributesEntity.AttributeMembersEntity> getSelectedEntities() {
        return this.selectedEntities;
    }

    public void setSelectedEntities(List<SelectSkuProductBean.AttributesEntity.AttributeMembersEntity> list) {
        this.selectedEntities = list;
    }
}
