(function () {
  "use strict";

  angular.module("portal").controller("ContactController", ContactController);

  function ContactController() {
    var vm = this;
    vm.success=false;

     vm.contact= function()
    {
vm.success=true;
    }


  }
})();
