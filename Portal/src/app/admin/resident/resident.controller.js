(function () {
  'use strict';
  angular.module("portal").controller("AdminResidentController", AdminResidentController);

  AdminResidentController.$inject = ["$stateParams", "adminDataService"];

  function AdminResidentController($stateParams, adminDataService) {
    var vm = this;

    function init() {
      vm.residentId = $stateParams.residentId;

      adminDataService.getAdminResident(vm.residentId)
        .then(function (response) {
          vm.resident = response;
        });
    }

    init();

  }

})();
