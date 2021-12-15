user = JSON.parse(localStorage.getItem("chat-dsi"));

$(window).on("load", function(){

  $("#titulo-chat").text(user.user_name.toUpperCase() + " - Contatos")
  setInterval(contacts, 1000);


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
    }else if(this.readyState == 4 && this.status == 404){

    }
  };
  var theUrl = url_api+"/messages/";
  xmlhttp.open("POST", theUrl, true);
  xmlhttp.setRequestHeader("Content-Type", "text/plain");
  xmlhttp.send(JSON.stringify({from_user: from_user, to_user: to_user}));
}