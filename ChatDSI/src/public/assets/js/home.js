user = JSON.parse(localStorage.getItem("chat-dsi"));

$(window).on("load", function(){
  let chatSelect = 0;
  contacts()
  $("#titulo-chat").text(user.user_name.toUpperCase() + " - Contatos")
  //setInterval(contacts, 3000);
  setInterval(loadMessages, 5000)



  function contacts(){
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.onreadystatechange = function() {
      let ul = ""
      if (this.readyState == 4 && this.status == 200) {
        data =  JSON.parse(this.response);
        data.contacts.forEach(item => {
          if(item.is_online == "true"){
            ul += `<li class="list-group-item item-contato" onclick="loadMessages(${user.user_id}, ${item.id})" id="${item.id}" ip="${item.ip}" port="${item.port}">${item.name} ° </li>`
          }else{
            ul += `<li class="list-group-item item-contato" onclick="loadMessages(${user.user_id}, ${item.id})" id="${item.id}" ip="${item.ip}" port="${item.port}">${item.name}</li>`
          }
        })

        $('#list-chat').html(ul);
      }else if(this.readyState == 4 && this.status == 404){

      }
    };
    var theUrl = url_api+"/contacts/";
    xmlhttp.open("POST", theUrl, true);
    xmlhttp.setRequestHeader("Content-Type", "text/plain");
    xmlhttp.send(JSON.stringify({user_id: user.user_id}));
  }

});

function loadMessages(from_user, to_user){

  if(to_user == 0)
    return
  if($("#conversa").attr('to') !=0 )
    to_user = $("#conversa").attr('to');

  if(!from_user)
    from_user = user.user_id

  $("#conversa").attr('to',to_user)

  try{
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.onreadystatechange = function() {
      let span = ""
      if (this.readyState == 4 && this.status == 200) {
        data =  JSON.parse(this.response);
        data.messages.forEach(item => {
          if(item.from_user != from_user){
            span += `
            <span class="msg-left col-md-8 p-3">
              ${item.content}
            </span>
            `
          }else{
            span += `
            <span class="msg-right align-self-end col-md-8 p-3">
            ${item.content}
            </span>
            `
          }
        })

        $('#conteudo-conversa').html(span);
        var objDiv = document.getElementById("conteudo-conversa");
        objDiv.scrollTop = objDiv.scrollHeight;

      }else if(this.readyState == 4 && this.status == 404){

      }
    };
    var theUrl = url_api+"/messages/";
    xmlhttp.open("POST", theUrl, true);
    xmlhttp.setRequestHeader("Content-Type", "text/plain");
    xmlhttp.send(JSON.stringify({from_user: from_user, to_user: to_user}));
  }catch(e){

  }
}


$("#BotaoEnviar").on("click", function(){
  let content = $('#inputMensagem').val()
  send(content, user.user_id, $("#conversa").attr('to'))
  $('#inputMensagem').val("")
})


function send(msg, from_user, to_user){
  try{
    var xmlhttp = new XMLHttpRequest();
    var theUrl ="http://localhost:9876";
    xmlhttp.onreadystatechange = function() {
      loadMessages(from_user, to_user)
    };
    xmlhttp.open("POST", theUrl, true);
    xmlhttp.setRequestHeader("Content-Type", "text/plain");
    xmlhttp.send(JSON.stringify({
      content:msg,
      from_user:from_user,
      to_user:to_user
    }));
  }catch(e){

  }
}