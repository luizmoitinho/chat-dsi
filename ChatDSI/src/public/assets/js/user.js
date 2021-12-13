
const url_api = "localhost:8000"

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

  $.ajax({
    type:"POST",
    url: url_api+"/create_user",
    data: values,
    dataType:jsonp
  })
  .done(function(data){
    console.log(data)
  })
  .fail(function(data){
    alert("Erro ao tentar conectar ao servidor.")
  })

})

