package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotCacheFile implements Serializable {
    private String fileName;
    private String filePath;
    private String fileSize;
    private int fileType;
    private boolean isCache;
    private String msgId;
    private int progress;
    private String snapshot;
    private int status = 0;
    private String url;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String str) {
        this.fileSize = str;
    }

    public boolean isCache() {
        return this.isCache;
    }

    public void setCache(boolean z) {
        this.isCache = z;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public String getSnapshot() {
        return this.snapshot;
    }

    public void setSnapshot(String str) {
        this.snapshot = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotCacheFile{msgId='" + this.msgId + DateFormat.QUOTE + ", filePath='" + this.filePath + DateFormat.QUOTE + ", fileName='" + this.fileName + DateFormat.QUOTE + ", fileType=" + this.fileType + ", progress=" + this.progress + ", url='" + this.url + DateFormat.QUOTE + ", fileSize='" + this.fileSize + DateFormat.QUOTE + ", isCache=" + this.isCache + ", status=" + this.status + '}';
    }
}
