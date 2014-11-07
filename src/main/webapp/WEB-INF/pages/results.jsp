
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>


<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickShare</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" >

    <link rel="stylesheet" href="<c:url value="/resources/css/normalize.css" />" >
    <link rel="stylesheet" href="<c:url value="/resources/css/foundation.css" />" >
    <link rel="stylesheet" href="<c:url value="/resources/css/foundation-icons.css" />" >


    <script src="<c:url value="/resources/js/vendor/jquery.js" />"></script>


</head>
<body>

<nav class="top-bar" data-topbar role="navigation">
    <ul class="title-area">
        <li class="name">
            <h1><a href="/">QuickShare</a></h1>
        </li>
    </ul>

    <section class="top-bar-section">
        <!-- Right Nav Section -->

        <ul class="right">
            <li class="has-form">
                <form action="/search" id="searchForm" method="get">

                <div class="row collapse">
                        <div class="large-8 small-9 columns">
                            <input type="text" name="q" id="q" placeholder="Search" class="search-input">
                        </div>
                        <div class="large-4 small-3 columns">
                            <button type="submit" id="button-search" class="button secondary"><i class="fi-magnifying-glass"></i></button>
                        </div>
                </div>
                </form>

            </li>
        </ul>

    </section>
</nav>
<div class="container">

    <!-- ########## Affichage des résultats de la recherche ########## -->
    <h2 class="text-center">Results for : ${query}</h2>
    <div class="row">
        <div class="large-6 large-centered columns">
            <ul class="small-block-grid-2 medium-block-grid-3 large-block-grid-4">

                <c:forEach var="image" items="${images}">
                    <li><a class="th" href="/details/${image.imgID}"><img src="http://i.imgur.com/${image.imgID}b.${image.format}" /></a></li>
                </c:forEach>

            </ul>
        </div>
    </div>

</div>

<footer>
    Developped by Alban Gonzalez & Albert Tran
</footer>

<script type="text/javascript">

    $('#searchForm').submit(function() {
        var query = $.trim($('#q').val());
        if (query.length > 0) {
            return true;
        }
        else {
            return false;
        }
    });

</script>

</body>
</html>
