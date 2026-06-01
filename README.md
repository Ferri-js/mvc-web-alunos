# mvc-web-alunos

<!-- mvc-web-alunos/README.md -->

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![MVC](https://img.shields.io/badge/Padr%C3%A3o-MVC-2563EB?style=for-the-badge)

> **Projeto didático** que demonstra a separação rígida do padrão **Model–View–Controller**
> usando Spring Boot + Thymeleaf. Cada camada tem responsabilidade única e arquivos físicos
> distintos — ideal para consolidar Arquitetura de Software na prática.

---

## 📐 Arquitetura MVC

| Camada | Responsabilidade | Arquivo(s) no Projeto |
|---|---|---|
| **Model** | Representar dados do domínio e encapsular regras de negócio. Totalmente independente de HTTP e HTML. | `model/Aluno.java` |
| **View** | Apresentar dados ao usuário. Recebe informações prontas do Controller e apenas as exibe. Nunca valida ou acessa dados diretamente. | `templates/alunos-form.html` `templates/alunos-lista.html` |
| **Controller** | Receber requisições HTTP, coordenar Model e View. Decide qual View renderizar e quais dados injetar. Não contém regras de negócio nem gera HTML. | `controller/AlunoController.java` |

### Fluxo de uma requisição POST

```
Navegador
   │
   │  POST /alunos  (nome, matricula)
   ▼
AlunoController          ← CONTROLLER coordena
   │  new Aluno(nome, matricula)
   ▼
Aluno.java               ← MODEL valida ("Nome obrigatório")
   │  objeto criado
   ▼
AlunoController
   │  model.addAttribute("alunos", alunos)
   │  return "alunos-lista"
   ▼
alunos-lista.html        ← VIEW itera e exibe
   │
   ▼
Navegador  (resposta HTML)
```

---

## 🗂️ Estrutura do Projeto

```
mvc-web-alunos/
├── pom.xml                                          # Dependências Maven
├── README.md
└── src/
    └── main/
        ├── java/com/exemplo/mvc/
        │   ├── MvcApplication.java                  # Ponto de entrada
        │   ├── model/
        │   │   └── Aluno.java                       # MODEL
        │   └── controller/
        │       └── AlunoController.java             # CONTROLLER
        └── resources/
            ├── application.properties               # Configurações
            └── templates/
                ├── alunos-form.html                 # VIEW — formulário
                └── alunos-lista.html                # VIEW — listagem
```

---

## 🚀 Como Executar

### Pré-requisitos

> [!NOTE]
> Certifique-se de ter instalado:
> - **JDK 17+** → [Download OpenJDK](https://adoptium.net/)
> - **Maven 3.8+** (ou use o wrapper `mvnw` incluído pelo Spring Initializr)
> - **VS Code** com as extensões:
>   - `Extension Pack for Java`
>   - `Spring Boot Extension Pack`

---

### Opção 1 — VS Code (recomendada para iniciantes)

1. Abra a pasta do projeto no VS Code:
   ```
   File → Open Folder → selecione "mvc-web-alunos"
   ```

2. Aguarde o VS Code importar o projeto Maven (barra inferior ficará estável).

3. Abra o arquivo:
   ```
   src/main/java/com/exemplo/mvc/MvcApplication.java
   ```

4. Clique no botão **▶ Run** que aparece acima do método `main`.

---

### Opção 2 — Terminal

```bash
# Clone o repositório
git clone https://github.com/SEU_USUARIO/mvc-web-alunos.git
cd mvc-web-alunos

# Execute com Maven
mvn spring-boot:run
```

> [!TIP]
> Aguarde a mensagem `Started MvcApplication` no console — isso indica que o servidor subiu com sucesso.

---

### ✅ Acessar no navegador

```
http://localhost:8080/alunos
```

| O que você verá | O que acontece no MVC |
|---|---|
| Formulário de cadastro | View `alunos-form.html` renderizada pelo Controller |
| Preenche nome + matrícula e clica em "Cadastrar" | Controller recebe POST → chama Model → injeta lista na View |
| Tabela com alunos cadastrados | View `alunos-lista.html` itera `${alunos}` do Model |
| Campo nome vazio → mensagem de erro | Model lança `IllegalArgumentException` → Controller redireciona com flash attribute |

---

## 📦 Subindo no GitHub

```bash
git init
git add .
git commit -m "Projeto MVC Web - Cadastro de Alunos"
git branch -M main
git remote add origin https://github.com/SEU_USUARIO/mvc-web-alunos.git
git push -u origin main
```

---

## 📋 Checklist de Entrega

- [ ] Link do repositório GitHub público
- [ ] Projeto executa sem erros (`mvn spring-boot:run`)
- [ ] Separação física das camadas MVC nos diretórios corretos
- [ ] `Aluno.java` contém regra de negócio (validação do nome)
- [ ] `AlunoController.java` sem HTML ou regras de domínio
- [ ] Views com Thymeleaf sem lógica de negócio
- [ ] README explicando a arquitetura

---

## 🔑 Conceitos Demonstrados

| Conceito | Onde aparece |
|---|---|
| **Separação de responsabilidades** | Cada camada tem um único papel bem definido |
| **Regra de negócio no Model** | `Aluno.java` lança exceção se nome for vazio |
| **Controller como orquestrador** | `AlunoController` coordena sem fazer o trabalho das outras camadas |
| **View passiva** | Templates apenas exibem dados, nunca os calculam |
| **Injeção de dados Model → View** | `model.addAttribute("alunos", alunos)` |
| **Anotações Spring MVC** | `@Controller`, `@GetMapping`, `@PostMapping`, `@RequestParam` |
| **Template Engine** | Thymeleaf com `th:each`, `th:text`, `th:if` |

---

*Projeto criado para fins didáticos — disciplina de Arquitetura de Software.*
