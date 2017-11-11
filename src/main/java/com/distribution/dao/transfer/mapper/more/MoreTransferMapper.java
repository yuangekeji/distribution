package com.distribution.dao.transfer.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.transfer.mapper.TransferMapper;
import com.distribution.dao.transfer.model.Transfer;

import java.util.List;
import java.util.Map;

public interface MoreTransferMapper extends TransferMapper {

    /**
     * description 查詢總條數
     * @param page
     * @return
     */
    Integer getTransferListCount(Page page);

    /**
     * description 转账记录查询
     * @param page
     * @return
     */
    List<Transfer> getTransferList(Page page);

    /**
     * description 转账列表下载
     * @author sijeong
     * */
    List<Transfer> getExcelTransferList(Map map);
}