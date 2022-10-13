# Relatório do Analisador Preditivo Não-Recursivo

### Como utilizar o analisador, para gerar as saídas esperadas?


<p>Visando um correto funcionamento do analisador, quando está rodando, ele pede um nome de um arquivo, pelo terminal. Tal arquivo seria aquele que contém a sua gramática, que vai ser utilizada. Para dar tudo certo, devemos ter algumas regras voltadas para a sintaxe na hora de escrever a gramática, sendo elas: todo símbolo gerador deve estar em uma linha diferente de outro, o símbolo que divide os geradores e as produções deve ser um "-->" e as produções devem ser separados por um "|". Lembrando que toda essa parte dos símbolos terminais e não terminais, devem seguir como base, aquilo que está no enunciado do trabalho prático.</p>

> Exemplo de gramática
>
>> S --> aY <br />
>> Y --> xTY | E <br />
>> T --> FG <br />
>> G --> 2FG | E <br />
>> F --> 9Sk | a

No inicio passamos um tempo apenas pensando em como fazer, decidimos enfim começar implementando uma classe para o SimboloGerador e SimboloProducao que representariam as duas partes da nossa gramatica, para que conseguissemos partir para a análise de uma forma mais eficiente.

Também incluimos uma classe para o Leitor da Gramática, que por padrão já lê o arquivo, acha o simbolo inicial, se for o simbolo inicial inclui o $ no follow do mesmo e também já armazena as produções em seus respectivos Simbolos Geradores.

Dentro das classes SimboloGErador e SimboloProducao, inserimos metodos que auxiliam na adiçao de produções, follows e firsts nos mesmos, assim como os getters necessários para consumir os dados, adicionamos também na classe SimboloGerador uma verificação para sabermos se o mesmo contém simbolos não terminais, auxliando na criação das funções posteriormente.

É importante salientar que todos os passos seguidos estão de acordo com o passo a passo inserido no portal pela professora, que nos auxiliou durante todo o trabalho para fazer a verificação.

Na criação de todos os métodos decidimos pela utilizaçaõ de HashSets (Dicionários sem repetição de valor) para o auxilio do acesso às informações.

Para a determinação dos firsts e dos follows utilizamos o padrão do passo a passo sugerido pela professora e também seguindo as regras de determinação do First e Follow.
<br />
