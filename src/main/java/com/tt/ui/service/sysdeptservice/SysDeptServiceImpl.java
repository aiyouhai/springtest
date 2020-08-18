package com.tt.ui.service.sysdeptservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.tt.ui.pojo.SysDept;
import com.tt.ui.repository.SysDeptRepository;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptRepository sysDeptRepository;

	@Override
	public SysDept selectDept(long id) {
		SysDept sysDept = sysDeptRepository.selectDept(id);
		return sysDept;
	}

	@Override
	public List<SysDept> selectOne(long id) {
		// and 查询
		Example example = new Example(SysDept.class);
		Criteria c1 = example.createCriteria();
		c1.andEqualTo("deptName", "财务");
		c1.andEqualTo("id", id);
		// or查询
		/*
		 * Criteria c2 = example.createCriteria(); c2.andEqualTo("id", id);
		 * example.or(c2);
		 */
		List<SysDept> selectByExample = sysDeptRepository.selectByExample(example);
		return selectByExample;
	}

}
