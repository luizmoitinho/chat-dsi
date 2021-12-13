
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
    console.log(this)
    if (this.readyState == 4 && this.status == 200) {
       alert("autenticado")
    }
  };
  xmlhttp.open("POST", theUrl, true);
  xmlhttp.setRequestHeader("Content-Type", "text/plain");
  xmlhttp.send(JSON.stringify(values));

  e.preventDefault()

})



$('form[name=create-user]').submit(function(e){
  var $inputs = $('form[name=create-user] :input');
  var values = {};
  $inputs.each(function() {
      values[this.name] = $(this).val();
  });

  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
  var theUrl = url_api+"/create_user/";
  xmlhttp.open("POST", theUrl, true);
  xmlhttp.setRequestHeader("Content-Type", "text/plain");
  response = xmlhttp.send(JSON.stringify(values));
  console.log(response)
  e.preventDefault()

})