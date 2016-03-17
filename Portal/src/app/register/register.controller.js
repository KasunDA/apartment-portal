(function () {
  "use strict";

  angular.module("portal").controller("RegisterController", RegisterController);

  RegisterController.$inject = ["$state", "dataService", "errorService"];

  function RegisterController($state, dataService, errorService) {
    var vm = this;

    function init() {

    }

    init();

    vm.register = function () {
      var data = {
        firstName: vm.firstname,
        lastName: vm.lastname,
        email: vm.email,
        securityAnswer: vm.securityanswer,
        username: vm.username,
        password: vm.password
      }

      dataService.createAccount(data)
        .then(function () {
          errorService.showModalAlert("Account created successfully").then(function () {
            $state.go("login");
          });
        })
        .catch(function () {
          errorService.showModalAlert();
        });
    };

  }

})();
