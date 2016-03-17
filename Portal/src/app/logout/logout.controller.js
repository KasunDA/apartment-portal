(function () {
  "use strict";

  angular.module("portal").controller("LogoutController", LogoutController);

  LogoutController.$inject = ["$rootScope", "$state", "dataService", "errorService", "$localStorage"];

  function LogoutController($rootScope, $state, dataService, errorService, $localStorage) {
    var vm = this;


    function init() {
      delete $localStorage.global_role;
      delete $localStorage.global_auth;

      $rootScope.global_role = null;
      $rootScope.global_auth = null;
    }

    init();
  }
})();
