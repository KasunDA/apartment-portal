(function () {
  'use strict';
  angular.module("portal").controller("AdminResidentListController", AdminResidentListController);

  AdminResidentListController.$inject = ["adminDataService"];

  function AdminResidentListController(adminDataService) {
    var vm = this; //model is created

    function getResidentList() {  //call to database
      adminDataService.getResidentList().then(function (response) {
        vm.residentlist = response;
      });
    }

    function init() {
      getResidentList();
    }

    init();

  }
})();
