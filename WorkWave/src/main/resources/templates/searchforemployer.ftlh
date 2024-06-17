<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Work Wave</title>
    <link rel="stylesheet" href='../static/css/workerSearch.css'>

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


        <form action="/worker/findVacancyNext" class="form_data" method="post">

            <input type="text" class="search-input" name="vacancy_name" value="${professi!}" placeholder="Поиск вакансий">
            <button class="search-button">Найти работу</button>
        </form>



        <#list vacancies as vacancy>
            <div class="profession_block">
                    <a href=${action}?vacancy_id=${vacancy.id} class="resume_link">
                        <div>
                            <p class="text21">${vacancy.profession!}</p>
                            <div class="about">
                                <p class="text22">Офис: ${vacancy.office!}</p>

                                <p class="text23">График работы: ${vacancy.schedule!}</p>
                            </div>
                        </div>
                    </a>
            </div>
        </#list>



    </div>

</div>
</body>
</html>