
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
    toastr[severity](message, title);
}

var showConfirm = function(message,cancelLabel,confirmLabel, callbackFunction){
    bootbox.confirm(message, cancelLabel, confirmLabel,callbackFunction);
}

var showDeleteConfirm = function(){
    showConfirm('Confirma a exclusão definitiva deste registro?', 'Cancelar', 'Confirmar', function (result) {
        if(result){
            return true;
        }else{
            return false;
        }
    });
}
