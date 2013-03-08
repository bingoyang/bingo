package com.visfull.web.vo;

import java.io.Serializable;

public class TreeNode implements Serializable {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long pId;
    private String nodeName;
    private String target;
    private String url;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getpId() {
        return pId;
    }
    public void setpId(Long pId) {
        this.pId = pId;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    

}
