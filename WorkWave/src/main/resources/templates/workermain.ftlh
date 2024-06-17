<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Work Wave</title>
    <link rel="stylesheet" href='../static/css/WorkerMain.css'>

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
            <a href="#findWork" class="link">Найти работу</a>
            <a href="/worker" class="link">Работникам</a>
            <a href="/employer" class="link">Работодателям</a>
            <a href="/account" class="link">Аккаунт</a>


            <a href="/login">
                <button>Войти</button>
            </a>
            <a href="/worker/registration">
                <button>Регистрация</button>
            </a>

        </div>
    </div>

    <div class="main_section">
        <img src="./static/img/clientM.jpg" alt="Картинка">
        <h2>Поможем найти работу каждому</h2>
        <a href="/worker/resume">
            <button>Создать резюме</button>
        </a>
    </div>

    <div class="searching" id="findWork">
        <form action="/worker/findVacancy" method="post">

            <input type="text" class="search-input" name="vacancy_name" placeholder="Поиск по вакансиям">
            <button class="search-button">Найти работу</button>
        </form>
    </div>
</div>
</body>
</html>