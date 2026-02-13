<#import "template.ftl" as layout>

<@layout.registrationLayout displayInfo=true; section>

    <#-- Add Bootstrap in header -->
    <#if section == "header">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </#if>

    <#-- Replace only the form area -->
    <#if section == "form">

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-4">

                    <div class="card shadow-lg rounded-4">
                        <div class="card-body p-4">

                            <h3 class="text-center text-primary mb-4">
                                ${msg("loginTitle")}
                            </h3>

                            <#-- ERROR MESSAGE -->
                            <#if message?has_content>
                                <div class="alert alert-danger">
                                    ${message.summary}
                                </div>
                            </#if>

                            <form id="kc-form-login"
                                  action="${url.loginAction}"
                                  method="post">

                                <#-- VERY IMPORTANT: Hidden Fields -->
                                <#list login.hiddenFields as field>
                                    <input type="hidden"
                                           name="${field.name}"
                                           value="${field.value}" />
                                </#list>

                                <div class="mb-3">
                                    <label class="form-label">
                                        ${msg("username")}
                                    </label>
                                    <input type="text"
                                           name="username"
                                           class="form-control"
                                           value="${(login.username!'')}"
                                           autofocus />
                                </div>

                                <div class="mb-4">
                                    <label class="form-label">
                                        ${msg("password")}
                                    </label>
                                    <input type="password"
                                           name="password"
                                           class="form-control" />
                                </div>

                                <button class="btn btn-primary w-100">
                                    ${msg("doLogIn")}
                                </button>

                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>

    </#if>

</@layout.registrationLayout>
