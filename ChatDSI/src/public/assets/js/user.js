
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

  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
  var theUrl = url_api+"/authenticate/";
  xmlhttp.open("POST", theUrl);
  xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xmlhttp.send(JSON.stringify(values));

  e.preventDefault()
})

