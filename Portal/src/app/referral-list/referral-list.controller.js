(function () {
  'use strict';
  angular.module("portal").controller("ReferralListController", ReferralListController);

  ReferralListController.$inject = ["dataService"];

  function ReferralListController(dataService) {
    var vm = this; //model is created

    function getReferrals() {
      dataService.getReferrals()
        .then(function (response) {
          vm.referrals = response;
        });
    }

    function init() {
      getReferrals();
    }

    init();

  }
})();
