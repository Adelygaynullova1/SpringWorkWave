<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='../static/css/WorkerResumePageForEmployers.css'>

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


    <div class="buttons_area">
        <div class="dropdown">
            <button class="dropbtn">Пригласить</button>
            <div class="dropdown-content">

                <#if vacancies?has_content>

                        <#list vacancies as vacancy>
                            <input type="hidden" name="vacancy_id" id="vacancy" value="${vacancy.id}">
                            <input type="hidden" name="resume_id" id="resume" value="${resumes.id}">
                            <input type="hidden" name="worker_id" id="worker" value="${resumes.worker.id}">
                            <button type="button" onclick="sendInv(${vacancy.id},${resumes.id},${resumes.worker.id})" class="btnVacancy" id="btn${vacancy?counter}" >${vacancy.profession}</button>
                        </#list>

                <#else>
                <button type="submit" class="btnVacancy" >У вас нет вакансий</button>
                </#if>
            </div>
            <div id="err"></div>
        </div>
    </div>

<script>
    function sendInv(vid, rid, wid) {
        let buttons = document.getElementsByClassName("btnVacancy");
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function () {
                try {
                    const response = fetch("http://localhost:8096/employer/inviteOnVacancy", {
                        method: "POST",
                        body: JSON.stringify(data(vid, rid, wid)),
                        headers: {
                            'Content-type': 'application/json; charset=UTF-8',
                        },
                    }).then((response) => console.log(response));
                    if (response.ok) {
                        document.getElementById("err").innerHTML = "отправили";
                    } if (response) {
                        document.getElementById("err").innerHTML = "вы уже отправляли ";
                    }
                } catch (error) {
                    console.error('Ошибка при выполнении запроса:', error);
                }
            })
        }
    }

    function data(vacancy_id, resume_id,worker_id) {

        return {
            vacancy_id: vacancy_id,
            resume_id: resume_id,
            worker_id: worker_id
        }
    }
</script>
</div>
</body>
</html>