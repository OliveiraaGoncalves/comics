# Comic

O Comic é uma aplicação Android modularizada que utiliza boas práticas de programação, seguindo os princípios do Clean Architecture. Implementado no padrão MVVM, o aplicativo carrega uma lista de super-heróis da Marvel, utilizando o Retrofit como cliente HTTP para interação com a API.

## Estrutura do Projeto

- **app**: Módulo principal do aplicativo, contendo a lógica da camada de apresentação (Activities, Fragments, ViewModel).
- **data**: layer de dados responsável pela interação com a camada de dados externos, como a integração com o Retrofit.
- **domain**: layer do domínio contendo os casos de uso e lógica de negócios.
- **presentation**: layer que contém interfaces de usuário comuns, como widgets personalizados e extensões.
- **core-network**: Módulo que contém as implementações necessárias para realizar uma comunicação com a api.
  
<img width="894" alt="Captura de Tela 2023-08-12 às 10 22 56" src="https://github.com/OliveiraaGoncalves/comics/assets/20058035/a3bee3f7-3d2a-42be-8a92-dbfe4bf9c978">

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas antes de começar:

- Android Studio [link](https://developer.android.com/studio)
- AGP 8
- JDK 17

## Configuração do Ambiente

1. Clone este repositório.
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```
