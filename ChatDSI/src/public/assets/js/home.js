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
            ul += `<li class="list-group-item item-contato" onclick="loadChat(${user.user_id}, ${item.id})" id="${item.id}" ip="${item.ip}" port="${item.port}">${item.name} ° </li>`
          }else{
            ul += `<li class="list-group-item item-contato" onclick="loadChat(${user.user_id}, ${item.id})" id="${item.id}" ip="${item.ip}" port="${item.port}">${item.name}</li>`
          }
          console.log(item)
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
