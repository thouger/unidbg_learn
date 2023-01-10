package cn.missfresh.sherlock.bo;

import java.util.List;

public class ConfigBO {
    private int code;
    private ConfigResult data;

    public class ConfigKeyValue {
        private String name;
        private String value;

        public ConfigKeyValue() {
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }

    public class ConfigResult {
        private int encrypted;
        private int id;
        private List<ConfigKeyValue> items;

        public ConfigResult() {
        }

        public int getEncrypted() {
            return this.encrypted;
        }

        public int getId() {
            return this.id;
        }

        public List<ConfigKeyValue> getItems() {
            return this.items;
        }

        public void setEncrypted(int i) {
            this.encrypted = i;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setItems(List<ConfigKeyValue> list) {
            this.items = list;
        }
    }

    public int getCode() {
        return this.code;
    }

    public ConfigResult getData() {
        return this.data;
    }

    public boolean isSuccessful() {
        return this.code == 0;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(ConfigResult configResult) {
        this.data = configResult;
    }
}
