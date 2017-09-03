package com.distribution.service;

import com.distribution.dao.dictionary.mapper.more.MoreDictionaryMapper;
import com.distribution.dao.dictionary.model.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description 共同方法Service
 * @author Bright
 * */
@Service
public class CommonService {

    @Autowired
    private MoreDictionaryMapper moreDictionaryMapper;
    /**
     * description 查询字典表方法
     * @author Bright
     * @param dicType 字典所属模块
     * */
    public List<Dictionary> selectDictionary(String dicType){
        return moreDictionaryMapper.getDictionary(dicType);
    }
}
