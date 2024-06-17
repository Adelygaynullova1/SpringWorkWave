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


            <form action="/employer/findWorkerNext" class="form_data" method="post">

                <input type="text" class="search-input" name="resume_name" value="${professi}" placeholder="Поиск по резюме">
                <button class="search-button">Найти сотрудника</button>
            </form>



                <#list resumes as resume>
                    <div class="profession_block">
                            <a href=${action}?resume_id=${resume.id} class="resume_link">
                                <div>
                                    <p class="text21">${resume.profession!}</p>
                                    <div class="about">
                                        <p class="text22">Опыт работы: ${resume.workExperience!}</p>

                                        <p class="text23">Образование: ${resume.education!}</p>
                                    </div>
                                </div>
                            </a>
                    </div>
                </#list>



    </div>

</div>
</body>
</html>