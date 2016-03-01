(function () {
  'use strict';
  angular.module("portal").controller("AdminLeaseListController", AdminLeaseListController);

  AdminLeaseListController.$inject = ["adminDataService"];

  function AdminLeaseListController(adminDataService) {
    var vm = this;

    function getLeaseList() {
      adminDataService.getLeaseList()
        .then(function (response) {
          vm.leaselist = response;
        });
    }

    function init() {
      getLeaseList();
    }

    init();

  }

})();
