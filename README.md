# ConnectArt CRUD

Sistema CRUD desktop desenvolvido em Java utilizando `JOptionPane` para interface gráfica e armazenamento de dados em arquivo CSV.

## 📌 Sobre o projeto

O ConnectArt CRUD simula um sistema simples de cadastro de usuários para uma plataforma de aprendizado artístico.

O sistema permite:

* Criar contas
* Visualizar registros
* Editar usuários
* Apagar registros
* Gerar massa de dados automaticamente
* Validar emails e entradas do usuário

Os dados são armazenados no arquivo `accounts.csv`.

---

## 🛠️ Tecnologias utilizadas

* Java
* Swing (`JOptionPane`)
* FileWriter / FileReader
* BufferedReader
* Regex para validação de email
* CSV para persistência de dados

---

## 📂 Estrutura do projeto

```bash
Connect-Art-CRUD/
│
├── Main.java
├── InputValidator.java
├── CsvRandomGenerator.java
├── accounts.csv
└── README.md
```

---

## ⚙️ Funcionalidades

### ✅ Cadastro

Permite cadastrar:

* Nome completo
* Email
* Senha
* Tipo de conta:

  * Aluno
  * Professor
* Matéria de interesse

O sistema valida:

* Emails duplicados
* Formato do email
* Campos mínimos

---

### ✅ Visualização

Permite:

* Buscar usuário por email
* Exibir todos os registros usando a palavra:

```txt
todos
```

---

### ✅ Atualização

Permite editar:

* Nome
* Email
* Senha
* Matéria

---

### ✅ Exclusão

Permite apagar registros através do email do usuário.

---

### ✅ Gerador de dados

O arquivo `CsvRandomGenerator.java` gera automaticamente:

* 100 usuários aleatórios
* Emails
* Senhas
* Tipos de conta
* Matérias

Ideal para testes.

---

## ▶️ Como executar

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
```

### 2. Compile os arquivos

```bash
javac *.java
```

### 3. Execute o sistema

```bash
java Main
```

---

## 🧪 Gerar dados automáticos

Para gerar registros automaticamente:

```bash
java CsvRandomGenerator
```

---

## 📸 Interface

O sistema utiliza caixas de diálogo do `JOptionPane` para interação com o usuário.

---

## 📚 Conceitos praticados

Este projeto utiliza conceitos importantes de programação:

* CRUD
* Manipulação de arquivos
* Estruturas de repetição
* Métodos
* Validação de dados
* Orientação a objetos
* Tratamento de exceções
* Expressões regulares

---
