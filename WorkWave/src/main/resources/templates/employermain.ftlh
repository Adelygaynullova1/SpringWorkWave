<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Work Wave</title>
    <link rel="stylesheet" href='../static/css/EmployerMain.css'>

</head>
<body>
<div class="site">
    <div class="header">
        <div class="logo">
            <img src="../static/img/icons8-vaporwave-96.png" alt="Иконка">

        </div>
        <div class="menu">
            <h1 class="logo_name">Work Wave</h1>

            <a href="/exit" class="link">Выйти</a>
            <a href="#findWorker" class="link">Найти сотрудника</a>
            <a href="/worker" class="link">Работникам</a>
            <a href="/employer" class="link">Работодателям</a>
            <a href="/account" class="link">Аккаунт</a>


            <a href="/login">
                <button>Войти</button>
            </a>
            <a href="/employer/registration">
                <button>Регистрация</button>
            </a>


        </div>
    </div>

    <div class="main_section">
        <img src="./static/img/img.png" alt="Картинка">
        <h2>Найдём работников для вас</h2>
        <a href="/employer/vacancy">
            <button>Создать вакансию</button>
        </a>
    </div>

    <div class="searching" id="findWorker">
        <form action="/employer/findWorker" method="post">

        <input type="text" class="search-input" name="resume_name" placeholder="Поиск по резюме">
        <button class="search-button">Найти сотрудника</button>
        </form>
    </div>
</div>
</body>
</html>