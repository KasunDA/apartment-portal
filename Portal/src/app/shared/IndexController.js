(function () {
  "use strict";

  angular.module("portal").controller("IndexController", IndexController);

  IndexController.$inject = ["$state", "$location", ""];

  function IndexController($state, $location) {
    var vm = this;

    function init() {

      console.log($location.path())
      if ($location.path() === "/") {
        vm.hideheader = true;
      }
      console.log($state.name);
    }

    init();
  }
})();
