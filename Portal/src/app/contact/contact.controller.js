(function () {
  "use strict";

  angular.module("portal").controller("ContactController", ContactController);

  ContactController.$inject = ["dataService"];

  function ContactController(dataService) {
    var vm = this;
    vm.success = false;


    function contact() {

      var data = {
        firstName: vm.firstName,
        lastName: vm.lastName,
        emailAddress: vm.emailAddress,
        message: vm.message,
      };
      dataService.contact(data)
        .then(function (response) {
          vm.success = true;
        })
        .catch(function () {
          vm.success = false;
        });
    }

    vm.contact = function () {
      contact();
    };

  }
})();
