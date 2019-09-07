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
            <form action="search" method="get">
                <table>
                    <tr>
                        <td>richest
                            user: ${richest_user.name} ${richest_user.surName}
                        </td>
                        <td>
                            <div>
                                <button type="submit" name="command"
                                        value="get_richest_user">get richest
                                    user
                                </button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Sum of all accounts: ${sum_all_accounts}
                            </output>
                        </td>
                        <td>
                            <div>
                                <button type="submit" name="command"
                                        value="get_sum_all_accounts">get sum of all the
                                    accounts
                                </button>
                            </div>
                        </td>
                    </tr>
                </table>
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
