package com.tt.ui.repository;

import com.github.abel533.mapper.Mapper;
import com.tt.ui.pojo.SysDept;

public interface SysDeptRepository extends Mapper<SysDept> {

	SysDept selectDept(long id);

}
