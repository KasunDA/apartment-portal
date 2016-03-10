(function () {
  'use strict';
  angular.module("portal").controller("AdminAptEditController", AdminAptEditController);

  AdminAptEditController.$inject = ["adminDataService", "$stateParams", "errorService"];

  function AdminAptEditController(adminDataService, $stateParams, errorService) {
    var vm = this; //model is created

    function getApt(apartmentId) {
      adminDataService.getApartment(apartmentId)
        .then(function (response) {
          vm.apt = response;
        });
    }

    function init() {
      vm.apartmentId = $stateParams.apartmentId;

      if (vm.apartmentId)
        getApt(vm.apartmentId);
      else {
        vm.apt = {};
      }
    }

    vm.save = function () {
      adminDataService.createApartment(vm.apt)
        .then(function () {
          errorService.showModalAlert("Apartment created successfully");
        })
        .catch(function () {
          errorService.showModalAlert();
        });
    };

    init();

  }
})();
