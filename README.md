<p align=center>
   <h1>Chat para centralização de chamados no Departamento de Sistemas de Informação (DSI)</h1>
</p>

## Tema
  Desenvolvimento de uma aplicação para estabelecer a comunicação entre aluno e a secretaria do Departamento de Sistemas de Informação (DSI), permitindo que a comunidade possa acessar a plataforma, realizar um cadastro e estabelecer a comunicação com o DSI, sendo para esclarecer dúvidas, realizar solicitações e acompanhamento de processos ou enviar ou receber documentos. Mediante a isto, será possível manter o histórico de conversa e relações de documentos, centralizando a comunicação em um único canal e dispondo de recursos facilitadores tanto para o DSI, quanto por parte de quem necessita obter informações ou algum tipo de orientação.
  Tais chats, podem ser organizados em tópicos previamente estabelecidos, bem como: Dúvidas, Solicitação e Acompanhamento de Processos, Adiantamento de Disciplinas, e Solicitação e Acompanhamento de Estágio Obrigatório.


##  Motivação
  Graças a novas ferramentas tecnológicas, a comunicação por meio destas se tornam cada vez mais presentes no cotidiano das pessoas, permitindo que sejam estabelecidas conversas e troca de conteúdos sem que haja a necessidade do deslocamento e/ou agendamento de um horário para resolução de um problema ou até mesmo para consulta de uma informação, esta que muitas vezes que poderia ser feita de forma remota e assíncrona, ou seja, solicitado o contato e enquanto não obtém o retorno, pode estar desempenhando outras responsabilidades do dia.
  Hoje, uma instituição é capaz de oferecer diferentes canais de comunicação, tanto para os clientes quanto para os próprios funcionários, e uma das ferramentas mais utilizadas para esse contato é o chat.
  O chat é uma forma prática e mais imediata de comunicação, que pode ser utilizada tanto entre os colaboradores de uma organização quanto entre atendentes (DSI) e clientes (população).


##  Aplicação
 Pretende-se desenvolver uma aplicação Web que estabelecerá a comunicação com uma API (do inglês, Application Interface Programming) desenvolvida na linguagem Java, esta que por sua vez, irá prover de recursos que permitam estabelecer a comunicação e troca de mensagens e demais conteúdos a serem enviados no chat, bem como o acesso a histórico dos chats. 
  Tais chats estariam agrupados em abas de acordo com o tópico de abertura do chat, como já foi citado anteriormente no tópico “Tema”, facilitando a organização e controle das principais demandas a serem atendidas e/ou resolvidas.
   Para tanto, a fim de manter os registros dos dados que serão inseridos na plataforma, pretende-se utilizar o PostgreSQL, este que é um sistema gerenciador de banco de dados objeto relacional (SGBD), desenvolvido como projeto de código aberto. Permitindo assim,  a inserção e recuperação dos dados de forma a manter a confidencialidade e integridade dos mesmos, pois, o PostgreSQL conta com recursos que resolvem complicações como a concorrência dos dados e relacionamento entre os mesmos. 
