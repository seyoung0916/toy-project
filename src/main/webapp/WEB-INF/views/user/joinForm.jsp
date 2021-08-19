<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <form action="/user/join" method="POST">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
    </form>
    <%--버튼이 form 안에 있으면 submit이 되어서 밖으로 뺌--%>
    <button id="btn-save" class="btn btn-primary">회원가입 완료</button>
</div>
<%-- /하면 static을 자동으로 찾음 --%>
<script src="/blog/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>

</html>
