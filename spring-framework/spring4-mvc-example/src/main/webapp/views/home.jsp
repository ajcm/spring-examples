<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>PC Clinic</title>

    <spring:url value="/resources/core/css/hello.css" var="coreCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
</head>


<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">PC Clinic</a>
        </div>
    </div>
</nav>


<p>username: ${username}</p>

<div class="container">
    <div class="row">

            <div class="col-md-4">

                <h4>Please Login</h4>
            <!--  FORM -->
            <form:form method="post" action="/pcclinic/login" modelAttribute="login">
                <div class="form-group">
                    <label >Username</label>
                    <input type="text" class="form-control" id="username" name="username" >
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>

            </form:form>

        </div>
        <div class="col-md-8">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo. </p>
            <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
        </div>

    </div>

</div>




<hr>
    <footer>
        <p>&copy; Mkyong.com 2015</p>
    </footer>
</div>

<spring:url value="../resources/core/css/hello.js" var="coreJs" />
<spring:url value="../resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>