package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.StudentVO;

public interface StudentMapper {
	List<StudentVO> studentList();
	
	StudentVO selectOne(String sno);

	int insertStudent(StudentVO svo);
}
