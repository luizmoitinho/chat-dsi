
const url_api = "http://localhost:8000"

$('form[name=signin]').submit(function(e){
  var $inputs = $('form[name=signin] :input');
  var values = {};
  $inputs.each(function() {
      values[this.name] = $(this).val();
  });

  if(!values.login || !values.password){
    alert("Login ou senha não foram informados")
    e.preventDefault()
    return
  }

  if(values.login.length == "" || values.password.length == ""){
    alert("Login ou Senha devem estar preenchidos")
    e.preventDefault()
    return
  }

  var xmlhttp = new XMLHttpRequest();
  var theUrl = url_api+"/authenticate/";
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      response = JSON.parse(this.response)
      user = {
        user_id: response.user_id,
        socket_ip: response.ip,
        socket_port: response.port
      }
      localStorage.setItem("chat-dsi", JSON.stringify(user));
      location.href = "home.html"
    }else if(this.readyState == 4 && this.status == 401){
      alert("Login e/ou senha estão incorretos.")
    }
  };
  xmlhttp.open("POST", theUrl, true);
  xmlhttp.setRequestHeader("Content-Type", "text/plain");
  xmlhttp.send(JSON.stringify(values));

  e.preventDefault()

})


$('#login').on("change", function(){
  var value = {
    login: $(this).val()
  };

  var xmlhttp = new XMLHttpRequest();
  var theUrl = url_api+"/exist_login/";
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     $('#register').attr("disabled", true);
    }else if(this.readyState == 4 && this.status == 204){
     $('#register').attr("disabled", false);
    }
  };
  xmlhttp.open("POST", theUrl, true);
  xmlhttp.setRequestHeader("Content-Type", "text/plain");
  xmlhttp.send(JSON.stringify(value));
})

$('form[name=create-user]').submit(function(e){
  var values = {
    name: $('#name').val(),
    login: $('#login').val(),
    password: $('#password').val(),
  };


  let msg =''

  if(values.name == ''){
    msg +=  'Campo Nome dever ser preenchido \n'
  }
  if(values.login == ''){
    msg +=  'Campo Login dever ser preenchido \n'
  }
  if(values.password == ''){
    msg +=  'Campo Senha dever ser preenchido \n'
  }
  if($('#confirm').val() == ''){
    msg +=  'Campo Repetir Senha dever ser preenchido \n'
  }

  if(values.password != $('#confirm').val()){
    msg +=  'As senhas não são iguais \n'
  }

  if(msg!=''){
    alert(msg)
    e.preventDefault()
  }else{
      console.log(values)
      var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
      xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 201) {
          alert("Conta criada com sucesso!")
          window.location.replace("index.html");
        }else if(this.readyState == 4 && this.status == 401){
          alert("Não possível criar um novo usuário.")
        }
      };
      var theUrl = url_api+"/create_user/";
      xmlhttp.open("POST", theUrl, true);
      xmlhttp.setRequestHeader("Content-Type", "text/plain");
      response = xmlhttp.send(JSON.stringify(values));
      console.log(response)
      e.preventDefault()

  }

})

function Sair() {
  user = JSON.parse(localStorage.getItem("chat-dsi"));
  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 204) {
      localStorage.removeItem("chat-dsi");
      window.location.replace("index.html");
    }else if(this.readyState == 4 && this.status == 401){
      alert("Ocorreu um erro ao realizar a operação.")
    }
  };
  var theUrl = url_api+"/logout/";
  xmlhttp.open("POST", theUrl, true);
  xmlhttp.setRequestHeader("Content-Type", "text/plain");
  response = xmlhttp.send(JSON.stringify({user_id: user.user_id }));


}