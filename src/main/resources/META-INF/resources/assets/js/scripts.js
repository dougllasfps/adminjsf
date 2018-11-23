
//https://codeseven.github.io/toastr/demo.html

toastr.options = {
    "closeButton": true,
    "debug": false,
    "newestOnTop": false,
    "progressBar": true,
    "positionClass": "toast-top-right",
    // "positionClass": "toast-top-full-width",
    // "positionClass": "toast-bottom-full-width",
    "preventDuplicates": true,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

var toarstMessage = function(severity, message, title){
    if(!severity){
        severity = 'error'
    }

    if(!message){
        message = 'ocorreu um erro.'
    }

    if(!title){
        title = 'Erro'
    }

    toastr[severity](message, title);
}

var showLoginErrorMessage = function(){
    toarstMessage('error', 'Usuário e/ou senha incorreto(s). Tente novamente.', 'Erro');
}