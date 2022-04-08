const cep = document.getElementById("cep");
        cep.addEventListener('blur', function(){
            let valor = document.getElementById("cep").value.replace(/-/g, "");
            fetch(`https://viacep.com.br/ws/${valor}/json/`).then((response) => {
                response.json().then(data => {
                    console.log(data);
                    const logradouro = document.getElementById("logradouro");
                    logradouro.value = data.logradouro;
                    const bairro = document.getElementById("bairro");
                    bairro.value = data.bairro;
                    bairro.focus();
                    const cidade = document.getElementById("cidade");
                    cidade.value = data.localidade;
                    cidade.focus();
                    const estado = document.getElementById("estado");
                    estado.value = data.uf;
                    estado.focus();
                    document.getElementById("numero").focus();
                });
            });
        });