(function () {
  "use strict";

  angular.module("portal").controller("AmenitiesController", AmenitiesController);

  AmenitiesController.$inject = ["dataService", "errorService"];

  function AmenitiesController(dataService, errorService) {
    var vm = this;


    function init() {
      vm.slides = ['../../assets/images/apartments/amenities/apt1.jpg',
        '../../assets/images/apartments/amenities/apt2.jpg',
        '../../assets/images/apartments/amenities/apt3.jpg',
        '../../assets/images/apartments/amenities/apt4.jpg']
    }

    init();
  }
})();
