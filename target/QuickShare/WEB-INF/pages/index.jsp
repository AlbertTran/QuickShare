
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

    <!-- ########## Formulaire d'upload ########## -->
    <div class="row">
        <form action="/upload" method="post" class="uploadForm" id="uploadForm" enctype="multipart/form-data">
            <div class="large-10 large-centered columns">
                <div class="row">
                    <div class="medium-10 columns">
                        <label id="file-label">Image <small>(Maximum allowed size : 2Mb)</small></label>
                    </div>
                </div>
                <div class="row">
                    <div class="medium-10 columns">
                        <div class="row collapse">
                            <div class="small-11 columns">
                                <input type="text" id="uploadFile" placeholder="Choose an image..." readonly />
                                <small id="size-error" hidden></small>

                            </div>

                            <div class="small-1 columns">
                                <div class="uploadButton button postfix">
                                    Browse
                                    <input type="file" name="image" id="uploadBtn" accept="image/*" />
                                </div>
                            </div>

                        </div>
                    </div>


                    <div class="medium-2 columns">
                        <button type="submit" id="validation-button" class="button submitButton postfix" disabled>
                            <i class="fi-upload" disabled></i> Upload
                        </button>
                    </div>

                </div>
                <div class="row">
                    <div class="medium-10 columns">
                        <label><input type="checkbox" name="isPrivate" value="true" /> Private <small class="no-capitalize">(Your image won't be able to be search)</small></label>
                    </div>
                </div>

                    <div class="row">
                        <div class="medium-4 columns">
                            <label for="title">Title <small>(Default : file name)</small></label>
                            <input type="text" id="title" name="title" placeholder="Title of the image" disabled="disabled" maxlength="40" />
                        </div>
                  <!--      <div class="medium-2 left columns">
                           <small> <input type="checkbox" />Private</small>
                        </div> -->


                    </div>

                </div>

        </form>

    </div>


    <!-- ########## Affichage des dernières images uploadées ########## -->
    <h2 class="text-center">Last uploads</h2>
    <div class="row" >
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


    $('#uploadBtn').bind('change', function() {

        var fileName = this.value.replace(/\\/g, '/').replace(/.*\//, '');
        $('#uploadFile').val(fileName);

        //Vérification taille du fichier
        var fileSize = this.files[0].size / (1024*1024);

        if (fileSize > 2) {

            $('#size-error').html("Size of current image : " + fileSize.toFixed(2) + "Mb - Maximum allowed size : 2Mb");

            if(!$('#file-label').hasClass("error")) {

                $('#size-error').addClass("error").css({"display": "block", "opacity": "0"}).animate({"opacity": "1"}, 1000);
                $('#file-label').addClass("error");
                $('#title').val("").prop('disabled', true);
                $('#validation-button').prop('disabled', true);
            }
        }
        else {

            $('#size-error').removeClass("error").css("display","none");
            $('#file-label').removeClass("error");
            $('#validation-button').prop('disabled', false);


            //Création d'un titre par défaut
            fileName = (fileName.substr(0, fileName.lastIndexOf('.')) || fileName).replace(/[_\-]/g, ' ');
            fileName = fileName.replace(/([a-z])([A-Z])/g, '$1 $2');
            fileName = fileName.trim().substring(0,40);

            $('#title').prop('disabled', false).val(fileName);


        }

    });

    $('#uploadForm').submit(function() {
        var title = $.trim($('#title').val());

        if (title.length == 0) {
            //Création d'un titre par défaut
            title = (title.substr(0, title.lastIndexOf('.')) || title).replace(/[_\-]/g, ' ');
            title = title.replace(/([a-z])([A-Z])/g, '$1 $2');
            title = title.trim().substring(0, 40);
            $('#title').val(title);

        }

        return true;

    });

</script>

</body>
</html>
