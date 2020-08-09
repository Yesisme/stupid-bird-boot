package com.epsoft.demo.dao;

import com.epsoft.demo.bean.po.Son;
import tk.mybatis.mapper.common.Mapper;

public interface SonMapper extends Mapper<Son> {

    Son selectById(Integer id);
}
