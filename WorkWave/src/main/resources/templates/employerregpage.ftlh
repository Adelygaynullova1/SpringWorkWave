<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SignUp</title>
    <link rel="stylesheet" href='../static/css/Reg.css'>

</head>
<body>
<div class="site">

    <a href="/employer" >
        <div class="logo">
            <img src="../static/img/icons8-vaporwave-96.png" alt="Иконка">
        </div>
    </a>

    <div class="main_section">
        <h2 color="red">${error!}</h2>
        <div class="main_section_login">
            <form action="/employer/registercheck" method="post" >

                <p class="inf_text">
                    Логин:
                </p>
                <input type="text" name="username" placeholder="введите имя пользователя">

                <p class="inf_text">
                    Пароль:
                </p>
                <input type="text" name="password">

                <p class="inf_text">
                    Имя:
                </p>
                <input type="text" name="name">

                <p class="inf_text">
                    Фамилия:
                </p>
                <input type="text" name="surname">

                <p class="inf_text">
                    Отчество:
                </p>
                <input type="text" name="patronymic">

                <p class="inf_text">
                    Название компании:
                </p>
                <input type="text" name="companyName">

                <p class="inf_text">
                    ИНН:
                </p>
                <input type="text" name="inn">


                <div class="btn_into">
                    <input type="submit" value="зарегистрироваться" class="btn_into_input">
                </div>
            </form>
        </div>

        <div class="main_section2">
            <p class="inf_text">
                Уже есть аккаунт?
            </p>
            <a href="/login">Войти</a>
        </div>

    </div>
</div>
</body>
</html>