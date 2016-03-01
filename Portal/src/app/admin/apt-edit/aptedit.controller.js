(function () {
  'use strict';
  angular.module("portal").controller("AdminAptEditController", AdminAptEditController);

  AdminAptEditController.$inject = ["adminDataService", "$stateParams"];

  function AdminAptEditController(adminDataService, $stateParams) {
    var vm = this; //model is created

    function getApt(apartmentId) {
      adminDataService.getApartments(isActive)
        .then(function (response) {
          vm.aptlist = response;
        });
    }

    function init() {
      vm.apartmentId = $stateParams.apartmentId;

      if (vm.apartmentId)
        getApt(vm.apartmentId);
      else {

      }
    }

    init();

  }
})();
