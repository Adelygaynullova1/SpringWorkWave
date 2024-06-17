<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='../static/css/Vacancy.css'>
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
            <a href="/worker" class="link">Работникам</a>
            <a href="/employer" class="link">Работодателям</a>
            <a href="/account" class="link">Аккаунт</a>

        </div>
    </div>
    <div class="main_section">
        <div class="text">
            <#if error?has_content>
                <h2 style="color: red">${error}</h2>
            <#else>
                <h2>Заполните данные</h2>
            </#if>
        </div>

        <div class="main_form">

                <div class="orange-part"></div>

            <div class="main_form_block">
                <div class="main_section_login">
                    <form action="/employer/saveVacancy" method="post" >

                        <p class="inf_text">
                            Название вакансии:
                        </p>
                        <input type="text" name="profession" placeholder="вакансия" required>


                        <p class="inf_text">
                            Офис:
                        </p>
                        <input type="text" name="office" required>


                        <p class="inf_text">
                            Почта для связи:
                        </p>
                        <input type="email" name="email" required >

                        <p class="inf_text">
                            График работы:
                        </p>
                        <input type="text" name="schedule" required>

                        <p class="inf_text">
                            Необходимое образование:
                        </p>
                        <input type="text" name="education" required>

                        <p class="inf_text">
                            Необходимый опыт работы:
                        </p>
                        <input type="text" name="workExperience" required>

                        <p class="inf_text">
                            Описание вакансии:
                        </p>
                        <input type="text" name="aboutVacancy" required>

                        <p class="inf_text">
                            Навыки:
                        </p>
                        <input type="text" name="skills" required>

                        <div class="btn_into">
                            <input type="submit" value="сохранить и продолжить" class="btn_into_input">
                        </div>
                    </form>
                </div>


            </div>
        </div>

    </div>

</div>
</body>
</html>