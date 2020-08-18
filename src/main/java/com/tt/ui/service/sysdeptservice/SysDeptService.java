package com.tt.ui.service.sysdeptservice;

import java.util.List;

import com.tt.ui.pojo.SysDept;

public interface SysDeptService {

	SysDept selectDept(long id);

	List<SysDept> selectOne(long id);

}
