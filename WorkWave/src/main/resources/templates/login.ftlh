<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>signIn</title>
    <link rel="stylesheet" href='../static/css/login.css'>
</head>
<body>
<div class="site">

    <a href="/worker">
        <div class="logo">
            <img src="../static/img/icons8-vaporwave-96.png" alt="Иконка">
        </div>
    </a>



    <div class="main_section">
        <h2 color="red">${error!}</h2>
        <div class="main_section_login">
            <form action="/logincheck" method="post" >

                <p class="inf_text">
                    Логин:
                </p>
                <input type="text" name="username" placeholder="введите имя пользователя" required>

                <p class="inf_text">
                    Пароль:
                </p>
                <input type="password" name="password" required>

                <div class="btn_into">
                    <input type="submit" value="Вход" class="btn_into_input">
                </div>
            </form>
        </div>

        <div class="main_section2">
            <p class="inf_text">
                Впервые у нас?
            </p>
            <a href="/worker/registration">Регистрация как работника</a>
            <a href="/employer/registration">Регистрация как работодателя</a>
        </div>
    </div>
</div>
</body>
</html>