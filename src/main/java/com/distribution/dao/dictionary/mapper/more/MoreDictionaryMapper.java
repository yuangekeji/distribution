package com.distribution.dao.dictionary.mapper.more;

import com.distribution.dao.dictionary.model.Dictionary;
import java.util.List;

public interface MoreDictionaryMapper {
    /**
     * description 根据字典所属模块，查询字典表
     * @author Bright
     * */
    List<Dictionary> getDictionary(String dicType);
}