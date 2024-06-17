<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='../static/css/VacancyPage.css'>
</head>

<body>
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

<div class="section_resumes">
    <div class="nsp">
        <div class="resume-nsp_text">
            <p>Данные работодателя:</p>
            <p class="text1">Фамилия: ${vacancy.employer.surname!}</p>
            <p class="text1">Имя: ${vacancy.employer.name!}</p>
            <p class="text1">Отчество: ${vacancy.employer.patronymic!}</p>
        </div>
    </div>
    <p class="text">Название вакансии: ${vacancy.profession!}</p>
    <div class="gray-line"></div>
    <p class="text">Как с нами связаться: ${vacancy.email!}</p>
    <div class="gray-line"></div>
    <p class="text"> Офис: ${vacancy.office!}</p>
    <div class="gray-line"></div>
    <p class="text">График работы: ${vacancy.schedule!}</p>
    <div class="gray-line"></div>
    <p class="text">Необходимое образование: ${vacancy.education!}</p>
    <div class="gray-line"></div>
    <p class="text"> Необходимый опыт работы: ${vacancy.workExperience!}</p>
    <div class="gray-line"></div>
    <p class="text">Описание вакансии: ${vacancy.aboutVacancy!}</p>
    <div class="gray-line"></div>
    <p class="text">Необходимые навыки: ${vacancy.skills!}</p>

</div>



</body>
</html>