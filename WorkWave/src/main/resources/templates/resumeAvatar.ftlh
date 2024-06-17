<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='../static/css/ResumeAvatar.css'>
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

<div class="main_section">
    <div class="text">
        <h2>Добавьте своё фото</h2>
    </div>

    <div class="main_form">
        <div class="orange-divider">
            <div class="orange-part"></div>
            <div class="white-divider"></div>
            <div class="gray-part"></div>
        </div>
        <form method="post" enctype="multipart/form-data" action="/worker/loadavatar">
            <div>
                <img src="${app_path!}${avatar!}" height="150" alt="Аватарка">

            </div>
            <input type="hidden" name="resume_id" value="${id!}">
            <input type="file" name="avatar">
            <input type="submit" value="Загрузить">
        </form>

    </div>

</body>
</html>