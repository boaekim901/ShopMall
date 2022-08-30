<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="form-group last mb-4 email_input" >
	<label for ="memail" id="mailTxt">이메일을 입력해주세요</label>
	<input type="text" class ="form-control" name="memail" id="memail">
</div>

<button class ="btn btn-outline-primary" type ="button" id="checkEmail" name="checkEmail"> 인증번호</button>

<div class="form-group last mb-4 check_input" >
	<label for="memailconfirm" id="memailconfirmTxt">인증번호를 입력해주세요</label>
	<input type="text" class="form-control" id="memailconfirm">
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
   $("#checkEmail").click(function() {
      alert("클릭성공");
      $.ajax({
         type:'GET',
         url:'/mailConfirm',
         data:{"email" : $("#memail").val()},
         dataType:"json",
         success : function(data){
            alert("성공");
         }
      })
   })
})
</script>
</body>
</html>