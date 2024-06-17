<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='../static/css/ResumePage.css'>
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
        <img src="${app_path!}${avatar}" height="150" alt="Аватарка">

        <div class="resume-nsp_text">
            <p class="text1">Фамилия: ${resumes.worker.surname!}</p>
            <p class="text1">Имя: ${resumes.worker.name!}</p>
            <p class="text1">Отчество: ${resumes.worker.patronymic!}</p>

        </div>


    </div>
    <p class="text">Как со мной связаться: ${resumes.email!}</p>
    <div class="gray-line"></div>
    <p class="text">Пол: ${resumes.gender!}</p>
    <div class="gray-line"></div>
    <p class="text">Дата рождения: ${resumes.birthDate!}</p>
    <div class="gray-line"></div>
    <p class="text">Гражданство: ${resumes.citizenship!}</p>
    <div class="gray-line"></div>
    <p class="text">Город проживания: ${resumes.city!}</p>
    <div class="gray-line"></div>
    <p class="text">Профессия: ${resumes.profession!}</p>
    <div class="gray-line"></div>
    <p class="text">Образование: ${resumes.education!}</p>
    <div class="gray-line"></div>
    <p class="text">Опыт работы: ${resumes.workExperience!}</p>
    <div class="gray-line"></div>
    <p class="text">Навыки: ${resumes.skills!}</p>
    <div class="gray-line"></div>
    <p class="text">О себе: ${resumes.aboutMe!}</p>
</div>



</body>
</html>