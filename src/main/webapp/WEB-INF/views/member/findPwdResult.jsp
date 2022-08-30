<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="CSS/subpage.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="member/member.js"></script>
<style type="text/css">
body{   
  background-color:#B96DB5;
  font-family: Verdana;
}
#wrap{     
  margin: 0 20px;
}
h1 {
  font-family: "Times New Roman", Times, serif;
  font-size: 45px;
  color: #CCC;
  font-weight: normal;
}
input[type=button], input[type=submit] {
  float: right;
}
label {
  width: 150px;
}
</style>
<script type="text/javascript">
function idok(){
  self.close();
}
</script>
</head>
<body>
<div id="wrap">
  <h1>비밀번호 찾기 결과</h1>
  <form method=post name=formm id="pwd_form" style="margin-right:0" >
    User ID <input type=text name="id" value="${id}">   
    <div style="margin-top: 20px">   
      <c:if test="${message == 1}">
      	<label>Password</label> 
        <input type="password"  name="pwd" id="pwd"><br> 
        <label>Retype Password</label> 
        <input type="password"  name="pwdCheck" id="pwdcheck"><br> 
        <input type="button" value="확인" class="cancel" onclick="changePassword()">
      </c:if>
      <c:if test="${message==-1}">
             잘못된 사용자 정보입니다.
         <input type="button" value="확인" class="cancel" onclick="idok()">
      </c:if>
    </div>
    
  </form>
</div>  
</body>
</html>