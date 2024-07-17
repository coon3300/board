<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studentFrom.jsp</title>
</head>
<body>
  <h3>학생정보 등록화면</h3>
  <!-- jsp/studentForm.jsp -->
  
  <form action="../StudentAddServ">
    <table border="2">
      <tr>
        <th>학생번호</th>
        <td><input type="text" name="sno" id=""></td>
      </tr>
      <tr>
        <th>이름</th>
        <td><input type="text" name="sname" id=""></td>
      </tr>
      <tr>
        <th>연락처</th>
        <td><input type="text" name="sphone" id=""></td>
      </tr>
      <tr>
        <th>생일</th>
        <td><input type="date" name="sbirth" id=""></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <button type="submit">저장</button>
        </td>
      </tr>
    </table>
  </form>
</body>
</html>