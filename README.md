# GiroBlu 🚲⚡ — Sistema de Mobilidade Urbana Compartilhada

O **GiroBlu** é um sistema de retaguarda desenvolvido em **Java** para a gestão de uma frota de bicicletas (mecânicas e elétricas) e patinetes elétricos compartilhados na cidade de Blumenau. O sistema permite cadastrar estações e frotas, gerenciar recargas de usuários, controlar viagens, aplicar planos de desconto, processar manutenções e gerar relatórios financeiros e de uso.

Projeto acadêmico desenvolvido para a disciplina de **Programação Orientada a Objetos (POO)** do curso de Ciência da Computação — **FURB (Universidade Regional de Blumenau)**.

---

## 👥 Equipe de Desenvolvimento

| Desenvolvedor | Função / Foco principal | GitHub |
| :--- | :--- | :--- |
| **Victor** | Backend / Arquitetura Base / Code Review | [@viictor-h-amaral](https://github.com/viictor-h-amaral) |
| **Camila** | Backend / Domínio / Testes Unitários & UI | [@camila-warmeling](https://github.com/camila-warmeling) |

---

## 🚀 Tecnologias e Conceitos Utilizados

* **Linguagem:** Java 17+
* **Interface Gráfica:** Java Swing (Arquitetura em duas camadas desassociada da camada de negócio)
* **Persistência de Dados:** Arquivos locais (CSV / Binário)
* **Testes Automatizados:** JUnit 6
* **Conceitos POO:**
  * Encapsulamento de estados e comportamentos.
  * Herança, Classes Abstratas e Métodos Abstratos.
  * Polimorfismo sem cadeias de `if/else` ou `instanceof`.
  * Interfaces personalizadas.
  * Tratamento e criação de Exceções customizadas.

---

## 🛠️ Regras de Negócio do Sistema

### 🏢 Estações
* Cada estação possui nome e capacidade fixa de vagas.
* Veículos parados ou em manutenção ocupam vagas; veículos em viagem **não** ocupam vagas.
* Não é permitido cadastrar ou devolver veículos em uma estação lotada.

### 🚲 Frota e Veículos
| Tipo de Veículo | Desbloqueio | Por Minuto | Regras de Bateria |
| :--- | :--- | :--- | :--- |
| **Bicicleta Mecânica** | R\$ 1,00 | R\$ 0,15 | N/A |
| **Bicicleta Elétrica** | R\$ 2,00 | R\$ 0,30 | Consome 1%/min. Liberação com no mínimo 20%. |
| **Patinete Elétrico** | R\$ 3,00 | R\$ 0,40 | Consome 2%/min. Liberação com no mínimo 20%. |

### 💳 Usuários, Carteiras e Planos
* A carteira de créditos começa zerada e é abastecida por recargas positivas.
* **Plano Avulso:** Preço cheio da tabela.
* **Plano Mensal:** Isento de taxa de desbloqueio; primeiros 15 minutos são gratuitos.
* **Plano Universitário:** 50% de desconto sobre o valor total em bicicletas (patinetes são cobrados a preço cheio).
* Saldo mínimo de **R\$ 5,00** exigido para iniciar viagens.
* Débitos no encerramento de viagens podem deixar a carteira negativa.

---

## 🌿 Git Flow & Esteira de Desenvolvimento

Nós utilizamos uma esteira simplificada de Git para simular o ambiente profissional de desenvolvimento em dupla:

* **`main`**: Código de produção estável, pronto para avaliação final.
* **`homologacao`**: Integração de tarefas e testes antes do lançamento para a `main`.
* **`desenvolvimento`**: Branch base para criação de novas funcionalidades.
* **Branches de Feature (`feature/TASK-X.X-nome`)**: Criadas a partir de `desenvolvimento`.

> ⚠️ **Code Review (CR):** Nenhuma alteração entra direto nas branches principais. Todo o código da Camila passa por Pull Request (PR) analisado pelo Victor com sugestões pedagógicas e ajustes de arquitetura.

---

## 📂 Estrutura de Pastas do Projeto

```text
GiroBlu/
├── .vscode/             # Configurações da IDE
├── lib/                 # Dependências (.jar do JUnit 6)
├── src/
│   ├── model/           # Camada de Negócio (Entidades, Exceções, Planos, Veículos)
│   ├── persistence/     # Leitura e Escrita de Arquivos (CSV/Binário)
│   └── Main.java        # Ponto de entrada da aplicação
├── docs/                # Diagrama UML em PDF e Plano de Testes
├── .gitignore
└── README.md
```

---

## 🏃 Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/viictor-h-amaral/GiroBlu.git
   ```
2. Abra a pasta `GiroBlu` no VS Code ou sua IDE Java de preferência.
3. Certifique-se de que o JDK 17 (ou superior) está configurado.
4. Execute o arquivo `src/Main.java`.

### Executando os Testes Unitários
No VS Code ou IntelliJ, navegue até as classes de teste no pacote de testes e execute a suíte via JUnit 6. Todos os testes devem rodar com resultado verde (100% de sucesso).

---

## 📄 Licença e Uso Acadêmico
Projeto desenvolvido exclusivamente para fins didáticos no curso de Ciência da Computação da FURB (2026/2).
