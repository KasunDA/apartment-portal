(function () {
  "use strict";

  angular.module("portal").controller("LoginController", LoginController);

  LoginController.$inject = ["$rootScope", "$state", "dataService", "errorService", "$localStorage"];

  function LoginController($rootScope, $state, dataService, errorService, $localStorage) {
    var vm = this;

    vm.success = false;

    function validateUser() {
      dataService.validateUser(vm.emailAddress, vm.password)
        .then(function (response) {
          if (response.error) {
            errorService.showModalAlert(response.error);
          }
          else {
            var role = "";
            if (response.isAdmin)
              role = "ADMIN";
            else if (response.isGuest)
              role = "GUEST";
            else
              role = "RESIDENT";

            $localStorage.global_role = role;
            $localStorage.global_auth = true;


            $rootScope.global_role = $localStorage.global_role;
            $rootScope.global_auth = $localStorage.global_auth;


            if ($localStorage.redirectState) {
              $state.go($localStorage.redirectState, $localStorage.redirectStateParams);
              delete $localStorage.redirectState;
              delete $localStorage.redirectStateParams;
            }
            else if (role == "GUEST")
              $state.go("guest");
            else if (role == "RESIDENT")
              $state.go("resident");
            else if (role == "ADMIN")
              $state.go("admin-home");
          }
        });
    }

    function register() {

      var data = {
        username: vm.username || "",
        password: vm.password || "",
      };

      dataService.register(data)
        .then(function (response) {
          vm.success = true;
        })
        .catch(function () {
          vm.success = false;
        });
    }

    vm.register = function () {
      register();
    };

    vm.validateUser = function () {
      validateUser();
    };

    function init() {
    }

    init();
  }
})();
