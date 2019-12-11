<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>New Entry</title>
</head>

<body>
<div class="container mx-auto mt-5 p-5 shadow" style="width: 50%">
<H2>BOARD :: New Entry</H2>
<HR>
<a href="javascript:history.go(-1)"  class="btn btn-info">돌아가기</a> <P>
<form name=form1 method=post action="/Basic_Board_Project/BoardController">
<input type=hidden name="action" value="insert">

<div class="form-group">
	<label for="USERID">ID : </label>	
  	<input type="text" class="form-control" name="USERID">
</div>

<div class="form-group">
	<label for="PASSWORD">PW : </label>	
  	<input type="password" class="form-control" name="PASSWORD">
</div>

<div class="form-group">
	<label for="NAME">NAME : </label>	
  	<input type="text" class="form-control" name="NAME">
</div>

<button type="submit" class="btn btn-primary">생성</button>
<button type="reset" class="btn btn-warning">취소</button>
</form>

</div>
</body>
</html>