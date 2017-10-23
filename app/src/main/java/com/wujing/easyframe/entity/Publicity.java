package com.wujing.easyframe.entity;

import java.io.Serializable;

/**
 * 科普宣传
 */

public class Publicity implements Serializable {
    /**
     * 编号
     */
    String id;
    /**
     * 标题
     */
            String title;
    /**
     * 内容
     */
            String content;
    /**
     * 来源
     */
            String source;
    /**
     * 浏览量
     */
            int browseCount;
    /**
     * 状态
     */
            String status;
    /**
     * 序号
     */
            String nseq;
    /**
     * 创建人
     */
            String createUser;
    /**
     * 创建时间
     */
            String createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNseq() {
        return nseq;
    }

    public void setNseq(String nseq) {
        this.nseq = nseq;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


}
