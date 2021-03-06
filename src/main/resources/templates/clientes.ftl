<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerencia de Clientes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <div class="jumbotron">
            <h1>Gerenciamento de clientes</h1>
            <p>Welcome</p>
        </div>
        <#if alterar??>
        <form action="/clientes/alterar" method="POST">
        <input type="hidden" value="${(clienteAtual.id)!}" name="id">
        <#else>
        <form action="/clientes/salvar" method="POST">
        </#if>
            <div class="form-group">
                <label for="nome">Nome</label>
                <input value="${(clienteAtual.nome)!}" type="text" class="form-control" id="nome" name="nome">
                <#if nome??>
                    <div class="mt-1 alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Erro!</strong> ${nome}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="cpf">CPF</label>
                <input value="${(clienteAtual.cpf)!}" type="text" class="form-control" id="cpf" name="cpf">
                <#if cpf??>
                    <div class="mt-1 alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Erro!</strong> ${cpf}
                    </div>
                </#if>
            </div>
            <#if alterar??>
            <input type="submit" class="btn btn-warning" value="Alterar">
            <#else>
            <input type="submit" class="btn btn-primary" value="Salvar">
            </#if>
        </form>
    </div>
    <div class="mt-5">
        <table class="table table-striped">
            <thead>
                <tr>
                <th scope="col">Nome</th>
                <th scope="col">CPF</th>
                <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <#list clientes as cliente>
                <tr>
                    <td>${cliente.nome}</td>
                    <td>${cliente.cpf}</td>
                    <td>
                        <a href="/clientes/prepararAlterar?id=${cliente.id}" class="btn btn-warning">Alterar</a>
                        <a href="/clientes/excluir?id=${cliente.id}" class="btn btn-danger">Excluir</a>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>