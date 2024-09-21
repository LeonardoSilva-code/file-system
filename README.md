
# File System

Este é um projeto de sistema de arquivos baseado em Spring Boot, desenvolvido para demonstrar operações básicas de gerenciamento de diretórios e arquivos.


## Funcionalidades Principais

- Criação de diretórios e arquivos.
- Listagem de diretórios e arquivos.
- Navegação.
- Atualização e exclusão de arquivos e diretórios.
- Breadcrumb.


## Tecnologias Utilizadas

- Java 22: Linguagem de programação utilizada.
- Spring Boot: Framework principal.
- H2 Database: Banco de dados em memória.
- Lombok: Redução de código boilerplate.

## Rodando localmente

### Pré-requisitos
- Java 22 ou superior.
- Maven para gerenciamento de dependências.

Clone o projeto

```bash
  git clone https://github.com/LeonardoSilva-code/file-system.git
```

Entre no diretório do projeto

```bash
  cd file-system
```

Compilar e rodar com Maven: Se você tem o Maven instalado na sua máquina

```bash
  mvn spring-boot:run
```

Ou utilizando o wrapper do Maven que já vem no projeto:

```bash
  ./mvnw spring-boot:run
```

Acessar o sistema: O sistema será executado localmente no endereço:

```bash
  http://localhost:8080
```

### Rodando utilizando docker
Clone o projeto

```bash
  git clone https://github.com/LeonardoSilva-code/file-system.git
```

Entre no diretório do projeto

```bash
  cd file-system
```

Faça build da imagem

```bash
  docker build -t filesystem-app .
```

Rode o container na porta 8080
```bash
  docker run -d -p 8080:8080 filesystem-app
```

Acessar o sistema: O sistema será executado localmente no endereço:

```bash
  http://localhost:8080
```




## Documentação da API

#### Listar diretórios raiz:

```http
  GET /api/filesystem/root
```

#### Lista as pastas e aruivos dentro de um diretório

```http
  GET /api/filesystem/directory/${directory_id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `directory_id`      | `string` | **Obrigatório**. O directory_id do diretório que você quer |

#### Obter o caminho (breadcrumb) de um diretório:

```http
  GET /api/filesystem/breadcrumb/${directory_id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `directory_id`      | `string` | **Obrigatório**. O directory_id do diretório que você quer |

Criar um novo diretório:
```http
  POST /api/filesystem/directory
```

| Corpo da Requisição   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Obrigatório. Nome do novo diretório |
| `parentId`      | `string` | **Obrigatório. Id do diretório pai (null se o arquivo for criado na root) |

Criar um novo arquivo:
```http
    POST /api/filesystem/file
```
| Corpo da Requisição   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Obrigatório. Nome do novo arquivo |
| `parentId`      | `string` | **Obrigatório. Id do diretório pai (null se o arquivo for criado na root) |
| `sizeInBytes`      | `number` | **Obrigatório. Tamanho do arquivo |
| `extension`      | `string` | **Obrigatório. Extensão do arquivo |

Atualizar um diretório existente:
```http
     PATCH /api/filesystem/directory/${directory_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `directory_id`      | `string` | **Obrigatório**. O directory_id do diretório que você quer atualizar|

| Corpo da Requisição   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Obrigatório. Nome do novo diretório |
| `parentId`      | `string` | **Obrigatório. Id do diretório pai (null se o arquivo for criado na root) |

Atualizar um arquivo existente:
```http
    PATCH /api/filesystem/file/${file_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `file_id`      | `string` | **Obrigatório**. O file_id do arquivo que você quer atualizar|

| Corpo da Requisição   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Obrigatório. Nome do novo arquivo |
| `parentId`      | `string` | **Obrigatório. Id do diretório pai (null se o arquivo for criado na root) |
| `sizeInBytes`      | `number` | **Obrigatório. Tamanho do arquivo |
| `extension`      | `string` | **Obrigatório. Extensão do arquivo |

Deletar um diretório:
```http
    DELETE /api/filesystem/directory/${directory_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `directory_id`      | `string` | **Obrigatório**. O directory_id do diretório que você quer deletar (deve deletar todos os filhos)|

Deletar um arquivo:
```http
    DELETE /api/filesystem/file/${file_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `file_id`      | `string` | **Obrigatório**. O file_id do arquivo que você quer deletar|

## Constraints

 - Nâo deve ser possível criar duas pastas ou dois arquivos com o mesmo nome no mesmo diretório

