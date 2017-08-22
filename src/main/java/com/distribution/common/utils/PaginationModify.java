package com.distribution.common.utils;

public class PaginationModify extends Pagination{
    //封装前台分页框架选择的查询行数
    private long selectedPageCount;

    public void setSelectedPageCount(long selectedPageCount) {
        this.selectedPageCount = selectedPageCount;
    }

    public long getSelectedPageCount() {
        return selectedPageCount;
    }

    @Override
    public long getPageCount() {
        //不可能成立
        if (this.totalCount < 0L) {
            return -1L;
        } else {
            if(this.pageSize==0){
                this.pageSize=1;
            }
            //因为为int运算，最终结果为整数，小数部分舍弃
            this.pageCount = this.totalCount / (long) this.pageSize;
            if (this.totalCount % (long) this.pageSize >= 0L) {

                //造成如此代码的原因在于前台的页数如果为小数，会自动加1
                //页数增加每页条数会发生减少，比如要求每页显示50天但求出的pageCount只有38条
                //比如114条总记录，每页显示50条

                //注意被除数小于除数时得到的结果为0，如果无该代码，当数据量小于10时，页面无数据
                if(this.pageCount==0){
                    this.pageCount=this.totalCount;
                } else if (this.pageCount != 0) {
                    if(this.selectedPageCount==10) this.pageCount = 10;
                    if(this.selectedPageCount==20) this.pageCount = 20;
                    if(this.selectedPageCount==30) this.pageCount = 30;
                    if(this.selectedPageCount==50) this.pageCount = 50;
                }
            }
            return this.pageCount;
        }
    }
}
