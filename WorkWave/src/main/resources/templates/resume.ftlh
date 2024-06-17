<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='../static/css/Resume.css'>

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
            <div class="orange-divider">
                <div class="orange-part"></div>
                <div class="white-divider"></div>
                <div class="gray-part"></div>
            </div>
            <div class="main_form_block">
                <div class="main_section_login">
                    <form action="/worker/saveResume" method="post" >

                        <p class="inf_text">
                            Профессия:
                        </p>
                        <input type="text" name="profession" placeholder="ваша профессия" required>

                        <p class="inf_text">
                            Пол:
                        </p>
                        <input type="text" name="gender" required>

                        <p class="inf_text">
                            Город или регион, где живете:
                        </p>
                        <input type="text" name="city" required>

                        <p class="inf_text">
                            Гражданство:
                        </p>
                        <input type="text" name="citizenship" required>

                        <p class="inf_text">
                            Ваша почта:
                        </p>
                        <input type="email" name="email" required >

                        <p class="inf_text">
                           Дата рождения:
                        </p>
                        <input type="date" name="birthDate" required>

                        <p class="inf_text">
                           Образование:
                        </p>
                        <input type="text" name="education" required>

                        <p class="inf_text">
                            Опыт работы:
                        </p>
                        <input type="text" name="workExperience" required>

                        <p class="inf_text">
                            О себе:
                        </p>
                        <input type="text" name="aboutMe" required>

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