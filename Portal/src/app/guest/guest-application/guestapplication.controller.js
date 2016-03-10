(function () {
  'use strict';
  angular.module("portal").controller("GuestApplicationController", GuestApplicationController);

  GuestApplicationController.$inject = ["dataService"];

  function GuestApplicationController(dataService) {
    var vm = this;
    vm.success = false;

    function application() {
      var data = {
        //name: vm.name,
        //phone: vm.phone,
        //email: vm.emailAddress,
        currentAddress: vm.currentAddress,
        aptType: vm.aptType,
        buildingNo: vm.buildingNo,
        aptNo: vm.aptNo,
      };


      dataService.application(data)
        .then(function (response) {

        })
        .catch(function () {
          vm.success = false;
        });
    }

    vm.application = function () {
      application();
    };


  }
})();
