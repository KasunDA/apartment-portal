(function () {
  'use strict';
  angular.module("portal").controller("AdminAptListController", AdminAptListController);

  AdminAptListController.$inject = ["adminDataService"];

  function AdminAptListController(adminDataService) {
    var vm = this; //model is created

    function getAptList(isActive) {  //call to database
      adminDataService.getApartments(isActive)
        .then(function (response) {
          vm.aptlist = response;
        });
    }

    function removeApt(apartmentId) {
      adminDataService.removeApartment(apartmentId)
        .then(function (response) {

        });
    }

    function init() {
      getAptList();
    }

    vm.remove = function (aptId) {
      removeApt(aptId);
    };

    init();

  }
})();
