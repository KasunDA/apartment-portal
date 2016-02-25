(function () {
  "use strict";

  angular.module("portal").controller("AptSearchController", AptSearchController);

  AptSearchController.$inject = ["dataService"];

  function AptSearchController(dataService) {
    var vm = this;

    vm.searchsuccess = false;

    function search() {

      var data = {
        noOfBedrooms: vm.noOfBedrooms || "",
        noOfBathrooms: vm.noOfBathrooms || "",
        patios: vm.patios || "",
      };

      dataService.search(data)
        .then(function (response) {
          vm.searchsuccess = true;
        })
        .catch(function () {
          vm.searchsuccess = false;
        });
    }

    vm.search = function () {
      search();
    };

    function init() {
    }

    init();
  }
})();
