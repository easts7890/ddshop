package com.ymd.ddshop.common.dto;

public class Page {
    private int page;

    private  int rows;

    //private  int offset;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * 获取偏移量，不需要手工设值
     * @return
     */
    public int getOffset() {
        return (page-1)*rows;
    }
}
