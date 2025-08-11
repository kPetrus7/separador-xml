# 🧾 Separador de Notas XML
Um projeto Java criado para automatizar a separação de notas fiscais eletrônicas (NF-e) em arquivos .xml, resolvendo o problema da separação manual enfrentado por profissionais da área administrativa e fiscal.

## 📌 Objetivo
Esse projeto nasceu a partir de uma necessidade real de uma colega, que precisava separar manualmente centenas de notas fiscais todos os meses. Com esse sistema, as notas são lidas, processadas e movidas automaticamente para pastas organizadas por dia, economizando tempo e evitando erros manuais.

## ⚙️ Funcionalidades:

📂 Leitura de arquivos .xml em lote.

📅 Extração da data de emissão da nota.

🗃️ Organização automática das notas em pastas por dia, nome de emissor e nº de nota.

📈 Log simples com informações de sucesso ou falha na leitura.

## 🛠️ Tecnologias utilizadas

JDK 24+

javax.xml (biblioteca para manipulação de XML)

org.w3c.dom (biblioteca para manipulação de DOM)

Java Swing (javax.swing) — para construção de interface gráfica

VS Code como ambiente de desenvolvimento

## 🚀 Como usar

### 1 Clone o repositório

git clone https://github.com/kPetrus7/separador-xml.git

### 2 Mande para sua área de trabalho

Localize o executável App.jar, e crie um atalho na sua Area de Trabalho

### 3 Salve as notas no formato XML

É importante que todas as notas estejam no mesmo diretório,
sem subpastas, da exata maneira como são baixadas dos programas da Receita Federal. Importante que os lotes das notas sejam salvos por mês (não misture mais de um mês por execução).

### 4 Inicie o programa

Para iniciar o programa, basta clicar duas vezes do atalho que você criou na sua área de trabalho.

### 5 Uso do programa

O programa vai pedir o diretório onde ele deve procurar as notas, basta copiar o caminho até a pasta com os XMLs, colar no campo e apertar Enter ou usar o explorador de arquivos do programa. Feito isso, você seleciona o tipo das notas entre entrada ou saida e marca se deseja renomear as arquivos com as informações das notas, fica a critério do usuário, depois basta clicar em Start.

### 6 Execução do programa

Durante a execução do programa, não faça alterações no diretório das notas, assim que terminar a execução você será avisado e poderá iniciar outra execução, caso deseje.

## 🙋‍♀️ Feito para ajudar
Este projeto foi desenvolvido especialmente para resolver o problema real de uma colega. Se ele também te ajudou, ⭐ dê uma estrela no repositório ou contribua com ideias!


