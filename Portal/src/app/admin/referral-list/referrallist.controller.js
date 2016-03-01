(function () {
  'use strict';
  angular.module("portal").controller("AdminReferralListController", AdminReferralListController);

  AdminReferralListController.$inject = ["adminDataService", "errorService"];

  function AdminReferralListController(adminDataService, errorService) {
    var vm = this;

    function getAdminReferralsList() {
      adminDataService.getAdminReferralsList()
        .then(function (response) {
          vm.referrals = response;
        });
    }

    function approve(referralId) {
      adminDataService.approveAdminReferral(referralId)
        .then(function (response) {
          if (response) {
            errorService.showModalAlert("Approved").then(function () {
              getAdminReferralsList();
            });
          }
          else
            errorService.showModalAlert();
        });
    }

    function init() {
      getAdminReferralsList();
    }

    vm.approve = function (referralId) {
      approve(referralId);
    };

    init();
  }
})();
