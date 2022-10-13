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
<br />
