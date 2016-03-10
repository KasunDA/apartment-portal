(function () {
  "use strict";

  angular.module("portal").controller("LogoutController", LogoutController);

  LogoutController.$inject = ["$rootScope", "$state", "dataService", "errorService"];

  function LogoutController($rootScope, $state, dataService, errorService) {
    var vm = this;


    function init() {
      $rootScope.global_auth = false;
    }

    init();
  }
})();
