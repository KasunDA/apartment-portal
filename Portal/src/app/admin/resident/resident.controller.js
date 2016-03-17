(function () {
  'use strict';
  angular.module("portal").controller("AdminResidentController", AdminResidentController);

  AdminResidentController.$inject = ["$state", "$stateParams", "adminDataService", "errorService"];

  function AdminResidentController($state, $stateParams, adminDataService, errorService) {
    var vm = this;

    function init() {
      vm.userId = $stateParams.userId;

      adminDataService.getGuests().then(function (data) {
        vm.guests = data;
      });

      adminDataService.getAdminAvailablePropertyList().then(function (data) {
        vm.properties = data;
      });
    }

    vm.saveresident = function () {
      adminDataService.setupResidentLease(vm.resident)
        .then(function (data) {
          if (data)
            errorService.showModalAlert("Lease setup completed").then(function () {
              $state.go("admin-resident-list");
            });
        })
        .catch(function () {
          errorService.showModalAlert();
        });
    };

    init();

  }

})();
