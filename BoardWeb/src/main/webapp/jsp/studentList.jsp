<%@page import="java.util.List"%>
<%@page import="com.yedam.mapper.StudentMapper" %>
<%@page import="com.yedam.vo.StudentVO" %>
<%@page import="com.yedam.common.DataSource"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studentList.jsp</title>
</head>
<body>
    <table border="2">
        <thead><tr><th>학번</th><th>이름</th><th>연락처</th></tr></thead>
        <tbody>
    <%
    //자바영역
    SqlSession sqlSession = DataSource.getInstance().openSession(true);
    StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
    List<StudentVO> list = mapper.studentList();
    for(StudentVO svo : list){
    %>
    <tr>
    <td><a href="student.jsp?sno=<%=svo.getStdNo() %>"><%=svo.getStdNo() %></a></td>
    <td><%=svo.getStdName() %></td>
    <td> <%=svo.getStdPhone() %></td>
    </tr>
    <%} %>
    </tbody>
    </table>
    <!-- HTML 영역. -->
</body>
</html>