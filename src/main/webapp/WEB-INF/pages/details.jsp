
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


    <c:set var="url" value="http://i.imgur.com/${imgID}.${format}" scope="page" />

        <!-- ########## Formulaire d'upload ########## -->
    <div id="img-section">
            <img src="<c:out value="${url}" />" class="img-detail" />
        <div class="img-title">
            <h3>${title}</h3>
        </div>
    </div>

    <div id="img-info">
        <div class="text-on-dark-background">
            <h2>Details</h2>
            <table>
                <tr><td>Published on :</td><td>${date}</td></tr>
                <tr><td>Dimensions :</td><td>${dimensions}</td></tr>
                <tr><td>Size :</td><td>${size} KB</td></tr>
                <tr><td>Type :</td><td><span class="capitalize">${format}</span></td></tr>
            </table>

            <h2>Links</h2>
            Direct Link
            <input readonly type="text" onclick="selectAll(this)" value="${url}" />
            BBCode (forums)
            <input readonly type="text" onclick="selectAll(this)" value="[IMG]${url}[/IMG]" />
            HTML
            <input readonly type="text" onclick="selectAll(this)" value="<img src=&quot;${url}&quot; title=&quot;${title}&quot;/>" />
        </div>
        <a href="${url}" download><button class="button small">Download</button></a>
    </div>




</div>

<footer>
    Developped by Alban Gonzalez & Albert Tran
</footer>


<script type="text/javascript">
    function selectAll(txtInput) {
        txtInput.setSelectionRange(0, txtInput.value.length)
    }

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
