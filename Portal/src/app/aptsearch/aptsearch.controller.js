(function () {
  "use strict";

  angular.module("portal").controller("AptSearchController", AptSearchController);

  AptSearchController.$inject = ["dataService", "errorService"];

  function AptSearchController(dataService, errorService) {
    var vm = this;

    vm.searchsuccess = false;

    function search() {

      var data = {
        bedrooms: vm.noOfBedrooms || "",
        bathrooms: vm.noOfBathrooms || "",
        garages: vm.garages || "",
      };

      dataService.search(data)
        .then(function (response) {
          vm.searchsuccess = true;
          vm.results = response;
        })
        .catch(function () {
          errorService.showModalAlert();
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
