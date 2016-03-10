(function () {
  'use strict';
  angular.module("portal").controller("AdminIssueListController", AdminIssueListController);

  AdminIssueListController.$inject = ["adminDataService"];

  function AdminIssueListController(adminDataService) {
    var vm = this;


    function getStaffList() {
      adminDataService.getStaffList()
        .then(function (response) {
          vm.staffList = response;
        });
    }

    function getIssues() {
      adminDataService.getIssues()
        .then(function (response) {

          for (var i = 0; i < response.length; i++) {
            response[i].closedDate = response[i].closedDate ? new Date(response[i].closedDate) : null;
          }

          vm.issues = response;
        });
    }

    function init() {
      vm.statuses = [{text: "pending", value: 1}, {text: "closed", value: 2}]
      getIssues();
      getStaffList();
    }

    vm.updateIssue = function (issue) {
      adminDataService.updateIssue(issue)
        .then(function () {

        });

    };

    init();

  }
})();
