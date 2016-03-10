(function () {
  'use strict';
  angular.module("portal").controller("ReferralController", ReferralController);

  ReferralController.$inject = ["dataService"];

  function ReferralController(dataService) {
    var vm = this; //model is created

    function init() {
    }

    function refer() {
      var data = {
        "guestName": vm.guestName,
        "emailAddress": vm.emailAddress,
        "phoneNumber": vm.phoneNumber,
        address: {
          address1: vm.address1,
          address2: vm.address2,
          city: vm.city,
          state: vm.state,
          zip: vm.zip
        }
      };

      dataService.referAFriend(data)
        .then(function (response) {

        });
    }

    vm.refer = function () {
      refer();
    };

    init();

  }
})();
