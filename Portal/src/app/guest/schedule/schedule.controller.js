(function () {
  'use strict';
  angular.module("portal").controller("GuestScheduleController", GuestScheduleController);

  GuestScheduleController.$inject = ["$filter", "dataService", "errorService"];

  function GuestScheduleController($filter, dataService, errorService) {
    var vm = this;

    function init() {
      vm.appointmentDate = new Date();
    }

    vm.getTimes = function () {

      var str = vm.appointmentDate ? $filter("date")(vm.appointmentDate, 'yyyy-MM-dd') : "";

      dataService.getAvailableAppointmentTimes(str)
        .then(function (response) {
          vm.availableTimes = response;
        });

    };

    vm.schedule = function () {

      var data = {
        name: vm.name,
        email: vm.email,
        phone: vm.phone,
        appointmentDate: vm.selectedTime
      };

      dataService.schedule(data)
        .then(function () {

          vm.success = true;

        })
        .catch(function () {
          errorService.showModalAlert();
        });

    };

    init();

  }
})();
