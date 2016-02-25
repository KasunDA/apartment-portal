(function () {
  "use strict";

  angular.module("portal").controller("AptViewController", AptViewController);

  AptViewController.$inject = ["$stateParams","dataService"];

  function AptViewController($stateParams, dataService) {
    var vm = this;

    function init() {
      //vm.apt = {
      //  AptId: 10,
      //  NoOfBedrooms: 5
      //};
      vm.aptId = $stateParams.aptId;
      getApartment(vm.aptId);
    }

    function getApartment(aptId){
      dataService.getApartment(aptId).then(function(response){
        vm.apt = response.data;
      });
    }

    vm.bookmarkApt = function (aptId) {
      var data = {
        aptId: aptId
      };
      dataService.bookmarkApt(data);
    };

    init();
  }
})();
