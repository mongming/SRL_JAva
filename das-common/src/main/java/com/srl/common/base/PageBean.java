/**
 *
 */
package com.srl.common.base;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 分页对象
 *
 * @Description:
 * @author: zwl
 * @date: 2018年8月22日 下午8:55:39
 */
public class PageBean<T> {

    private Integer draw = 0;

    private Integer iTotalRecords;

    private Integer iTotalDisplayRecords;

    /**
     * 从第几条记录开始查询
     */
    private Integer start = 0;
    /**
     * 每一页的记录数
     */
    private Integer length = 5;

    /**
     * 传递到页面的数据
     */
    private List<T> aaData;

    public PageBean(String startStr, String lengthStr, String drawStr) {
        if (StringUtils.isNotBlank(startStr)) {
            start = Integer.parseInt(startStr);
            length = Integer.parseInt(lengthStr);
        }
        if (StringUtils.isNotBlank(lengthStr)) {
            length = Integer.parseInt(lengthStr);
        }
        if (StringUtils.isNotBlank(drawStr)) {
            draw = Integer.parseInt(drawStr);
        }
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

}
