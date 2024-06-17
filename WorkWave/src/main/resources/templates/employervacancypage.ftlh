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


    <div class="buttons_area">
        <div class="dropdown">
            <button class="dropbtn">Откликнуться</button>
            <div class="dropdown-content">

                <#if resumes?has_content>

                    <#list resumes as resume>
                        <input type="hidden" name="vacancy_id" value="${vacancy.id}">
                        <input type="hidden" name="resume_id" value="${resume.id}">
                        <input type="hidden" name="employer_id" value="${vacancy.employer.id}">
                        <button type="button" onclick="sendInv(${vacancy.id},${resume.id},${vacancy.employer.id})" class="btnVacancy" id="btn${resume?counter}" >${resume.profession}</button>
                    </#list>

                <#else>
                    <button type="submit" class="btnVacancy" >У вас нет резюме</button>
                </#if>
            </div>
            <div id="err"></div>
        </div>
    </div>



    <script>
        function sendInv(vid, rid, eid) {
            let buttons = document.getElementsByClassName("btnVacancy");
            for (let i = 0; i < buttons.length; i++) {
                buttons[i].addEventListener('click', function () {
                    try {
                        const response = fetch("http://localhost:8096/worker/feedbackOnVacancy", {
                            method: "POST",
                            body: JSON.stringify(data(vid, rid, eid)),
                            headers: {
                                'Content-type': 'application/json; charset=UTF-8',
                            },
                        }).then((response) => console.log(response));
                        if (response.ok) {
                            document.getElementById("err").innerHTML = "отправили";
                        } else {
                            document.getElementById("err").innerHTML = "вы уже отправляли на это резюме";
                        }
                    } catch (error) {
                        console.error('Ошибка при выполнении запроса:', error);
                    }

                })
            }
        }

        function data(vacancy_id, resume_id,employer_id) {

            return {
                vacancy_id: vacancy_id,
                resume_id: resume_id,
                employer_id: employer_id
            }
        }
    </script>
</div>
</body>
</html>