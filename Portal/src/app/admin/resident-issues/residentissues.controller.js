(function () {
  'use strict';
  angular.module("portal").controller("AdminIssueListController", AdminIssueListController);

  AdminIssueListController.$inject = ["adminDataService"];

  function AdminIssueListController(adminDataService) {
    var vm = this;

    function getIssues() {
      adminDataService.getIssues()
        .then(function (response) {
          vm.issues = response;
        });
    }

    function init() {
      getIssues();
    }

    init();

  }
})();
