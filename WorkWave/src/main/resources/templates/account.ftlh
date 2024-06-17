<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мой Аккаунт</title>
    <link rel="stylesheet" href='../static/css/Account.css'></head>
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



    </div>
</div>
<#if employer?has_content!>

    <div class="main_section">
        <div class="account_main">
            <p class="text">${employer.surname!}</p>
            <p class="text">${employer.name!}</p>
            <p class="text">${employer.patronymic!}</p>
        </div>

        <div>
            <p class="text2">Мои вакансии:</p>
            <div class="section_resumes">

                <#if vacancies?has_content>

                    <#list vacancies as vacancy>
                        <a href=${action}?vacancy_id=${vacancy.id} class="resume_link">
                            <div class="section_resumes_block">
                                <div class="resume_profession"> ${vacancy.profession!}</div>
                            </div>
                        </a>
                    </#list>


                <#else>
                    <h2>У вас пока нет вакансий</h2>
                </#if>
            </div>
        </div>

    </div>
    <div class="block1">
        <div class="button_block">
            <a href="/employer/vacancy">
                <button>Создать вакансию</button>
            </a>
        </div>
        <p class="text3">Кого приглашали:</p>
        <div class="invitations">
            <#if invitations?has_content>

                <#list invitations as invitation>
                    <p class="text22">Сотрудник: ${invitation.resume.worker.surname!} ${invitation.resume.worker.name!} ${invitation.resume.worker.patronymic!}</p>
                    <p class="text22">На какую профессию: ${invitation.resume.profession!}</p>
                    <p class="text22">Как с ним связаться: ${invitation.resume.email!}</p>
                    <div class="gray-line"></div>
                </#list>

            <#else>
                <h2>Вы никого не приглашали</h2>
            </#if>
        </div>

        <p class="text3">Отклики:</p>
        <div class="invitations">
            <#if feedbacks?has_content>

                <#list feedbacks as feedback>
                    <p class="text22">Сотрудник: ${feedback.resume.worker.surname!} ${feedback.resume.worker.name!} ${feedback.resume.worker.patronymic!}</p>
                    <p class="text22">На какую профессию: ${feedback.vacancy.profession!}</p>
                    <p class="text22">Как с ним связаться: ${feedback.resume.email!}</p>
                    <div class="gray-line"></div>
                </#list>

            <#else>
                <h2>Пока нет откликов</h2>
            </#if>
        </div>
    </div>



<#else>
    <div class="main_section">

            <div class="account_main">
                <p class="text">${worker.surname!}</p>
                <p class="text">${worker.name!}</p>
                <p class="text">${worker.patronymic!}</p>
            </div>





        <div>
            <p class="text2">Мои резюме:</p>
            <div class="section_resumes">

                <#if resumes?has_content>

                    <#list resumes as resume>
                        <a href=${action}?resume_id=${resume.id} class="resume_link">
                            <div class="section_resumes_block">
                                <div class="resume_profession"> ${resume.profession!}</div>
                            </div>
                        </a>
                    </#list>

                <#else>
                    <h2>У вас пока нет резюме</h2>
                </#if>
            </div>
        </div>

    </div>

    <div class="block1">
        <div class="button_block">
            <a href="/worker/resume">
                <button>Создать резюме</button>
            </a>
        </div>
        <p class="text3">Мои приглашения:</p>
        <div class="invitations">
            <#if invitations?has_content>

                <#list invitations as invitation>
                    <p class="text22">Работодатель: ${invitation.employer.surname!} ${invitation.employer.name!} ${invitation.employer.patronymic!}</p>
                    <p class="text22">На какую профессию: ${invitation.vacancy.profession!}</p>
                    <p class="text22">Как с нами связаться: ${invitation.vacancy.email!}</p>
                    <div class="gray-line"></div>
                </#list>

            <#else>
                <h2>У вас пока нет приглашений</h2>
            </#if>
        </div>


        <p class="text3">Кому вы откликнулись:</p>
        <div class="invitations">
            <#if feedbacks?has_content>

                <#list feedbacks as feedback>
                    <p class="text22">Работадатель: ${feedback.vacancy.employer.surname!} ${feedback.vacancy.employer.name!} ${feedback.vacancy.employer.patronymic!}</p>
                    <p class="text22">На какую профессию: ${feedback.vacancy.profession!}</p>
                    <p class="text22">Как с ним связаться: ${feedback.vacancy.email!}</p>
                    <div class="gray-line"></div>
                </#list>

            <#else>
                <h2>Пока вы не откликались</h2>
            </#if>
        </div>
    </div>



</#if>
</body>
</html>