(function () {
  'use strict';
  angular.module("portal").controller("MainPageController", MainPageController);

  MainPageController.$inject = ["$state", "$rootScope", "$stateParams", "errorService"];

  function MainPageController($state, $rootScope, $stateParams, errorService) {
    var vm = this;

    function init() {

      vm.locations = [{title: 'Azure Apartments', value: 0},
        {title: 'Lakeheights', value: 1},
        {title: 'Kratel Apartments', value: 2},
      ];
    }

    vm.onLocationChange = function (location) {
      $state.go("location-home", {locationId: location.value});
      $rootScope.location = location;
    };

    init();

  }
})();
