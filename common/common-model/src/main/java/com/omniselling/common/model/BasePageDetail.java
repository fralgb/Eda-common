package com.omniselling.common.model;

import java.util.List;

public class BasePageDetail<T>
{

    /**
     * 总页数
     */
    private Integer total;
    
    /**
     * 每页记录数
     */
    private Integer rowsPerPage;
    
    /**
     * 查找记录的偏移量
     */
    private Integer offset;
    
    /**
     * 数据记录
     */
    private List<T> datas;
    
    public Integer getTotal()
    {
        return total;
    }
    public void setTotal(Integer total)
    {
        this.total = total;
    }
    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }
    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }
    public Integer getOffset()
    {
        return offset;
    }
    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }
    public List<T> getDatas()
    {
        return datas;
    }
    public void setDatas(List<T> datas)
    {
        this.datas = datas;
    } 

}
