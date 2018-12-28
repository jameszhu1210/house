package com.sysco.house.common.request;

/**
 * 分页基础请求类
 *
 */
public class BasePageRequestBean {
    /**
     *每页条数
     */
    private Integer limit;

    /**
     *起始记录
     * 第一页 0
     * 第二页10
     * 第三页 20
     *
     */
    private Integer offset;
    /**
     * 排序字段
     */
    private  String sort;
    /**
     * 升序或者降序  asc升序
     */
    private  String order;
    /**
     *升序或者降序
     */
    private Boolean  isDesc;


    public String getSort() {
        if(sort!=null){
            return camelToUnderline(sort);
        }

        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Boolean getDesc() {
        if(order!=null){
            if("asc".equals(order)){
                isDesc=false;
            }else {
                isDesc=true;
            }
        }
        if(isDesc==null){
            isDesc=true;
        }
        return isDesc;
    }

    public void setDesc(Boolean desc) {
        isDesc = desc;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
    public Integer getOffset() {
        if(offset==null) {
            return  1;
        }
        return offset/limit +1;
    }

    public Integer getRealOffset() {
        if(offset==null) {
            return  1;
        }

        return offset;
    }

    public static final char UNDERLINE = '_';

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

 }
