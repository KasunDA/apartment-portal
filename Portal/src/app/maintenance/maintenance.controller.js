(function () {
  'use strict';
  angular.module("portal").controller("MaintenanceController", MaintenanceController);

  MaintenanceController.$inject = ["dataService"];

  function MaintenanceController(dataService) {
    var vm = this; //model is created

    function getIssues() {  //call to database
      dataService.getIssues().then(function (response) {
        vm.issues = response.data;

      });
    }

    getIssues();

  }
})();
