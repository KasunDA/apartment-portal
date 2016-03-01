(function () {
  'use strict';
  angular.module("portal").controller("AdminResidentListController", AdminResidentListController);

  AdminResidentListController.$inject = ["dataService"];

  function AdminResidentListController(dataService) {
    var vm = this; //model is created

    function getResidentList() {  //call to database
      dataService.getResidentList().then(function (response) {
        vm.residentlist = response.data;

      });
    }

    getResidentList();

  }
})();
