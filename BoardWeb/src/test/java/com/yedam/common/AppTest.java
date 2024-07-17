package com.yedam.common;

import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.StudentMapper;

public class AppTest {
	
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		
		mapper.studentList();
		
		mapper.studentList().forEach(student -> {
			System.out.println(student);
		});
		
	}
}
