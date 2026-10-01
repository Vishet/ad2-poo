# AD2  - PROGRAMAÇÃO ORIENTADA A OBJETOS

## CLASSES PRINCIPAIS 

- **Leitura** 

  Classe básica que encapsulará os valores e data de uma leitura. 

- **Sensor** 

  Uma classe abstrata, contendo os métodos e atributos requisitados, assim como um método abstrato que terá a responsabilidade de classificar os valores das leituras, que será implementada pelas subclasses. 

- **SistemaMonitoramento** 

  Responsável por gerenciar e assegurar sensores com identificadores únicos, assim como fazer consultas e analises. 

## EXCEÇÕES 

- **IdInvalidException** 

  É lançada quando é feito uma tentativa de consultar um sensor inexistente com o id. 

- **IdConflitoException** 

  Lançada na tentativa de adicionar um sensor ao sistema sem um id único. 

- **SemLeituraException** 

  Lançada quando é tentado consultar uma leitura de uma coleção vazia. 

## DECISÕES 

- Foi criada uma classe abstrata SensorPositivo que irá ser herdada pelas classes SensorQualidadeAr e SensorNivelAgua. Essa classe garante que o valores das leituras sejam positivos, caso não seja, é lançada uma exceção. 

- Para não haver redundância, a classe SistemaMonitoramento não possui métodos como calcular média, pois o objetivo pode ser facilmente atingido acessando o sensor escolhido, como no exemplo: 

  ```java
  sistema.getSensor(id).getMediaLeituras();
  ```

- A estrutura do projeto segue a convenção dos pacotes Java. 

- Utilizou-se a interface Serializable para persistir o estado do sistema de monitoramento em um arquivo. 

- O programa possui um parâmetro opcional para especificar o carregamento de um arquivo já salvo anteriormente. 

- Foi utilizado um HashMap e um ArrayList para armazenar os sensores e as leituras, respectivamente. O HashMap, além de eficiente, previne sensores duplicados, usando a chave como o id do sensor. A ArrayList mantém a ordem cronológica das leituras, por isso foi escolhida. 

- Para as classificações das leituras, foi definido um Enum publico na classe Sensor, isso previne erros de digitações caso o tipo da classificação fosse uma String. 