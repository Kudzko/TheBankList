<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/view/style/style.css"%>
    </style>
</head>
<body>
<header>
    <div class="container">

        <div class="div1">
            div1
        </div>

        <div class="div2">
            div2
        </div>

    </div>
</header>

<main>
    <div class="container">
        <div class="div3">
            div3
        </div>

        <div class="div4">
            <form action="search" method="post">
                richest user:
                <output name="richest_user">no result yet (push the
                    button)</output>
                <output name="richest_user">${richest_user.name}
                    ${richest_user.surName}</output>

                <div>
                    <button type="submit" name="command"
                            value="get_richest_user">get richest user
                    </button>
                </div>



                richest user:
                <output name="sum_of_all_accounts">no result yet (push the
                    button)</output>
                <div>
                    <button type="submit" name="command"
                            value="get_sum_of_all_accounts">get sum of all the
                        accounts
                    </button>
                </div>
            </form>
        </div>

        <div class="div5">
            div5
        </div>
    </div>
</main>
<footer>

</footer>
</body>
</html>
