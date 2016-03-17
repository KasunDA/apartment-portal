(function () {
  'use strict';
  angular.module("portal").controller("AppointmentsController", AppointmentsController);

  AppointmentsController.$inject = ["adminDataService", "errorService"];

  function AppointmentsController(adminDataService, errorService) {
    var vm = this;

    function init() {
      vm.requestDate = Date.today();
      getAdminAppointments(vm.requestDate);
    }

    function getAdminAppointments(requestDate) {
      adminDataService.getAdminAppointments(requestDate)
        .then(function (data) {
          vm.appointments = data;
        });
    }

    vm.changeRequestDate = function () {
      getAdminAppointments(vm.requestDate);
    };

    init();

  }
})();
