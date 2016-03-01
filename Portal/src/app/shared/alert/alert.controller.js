angular
  .module("portal")
  .controller("AlertController", function ($scope, $modalInstance, message) {
    var vm = this;
    vm.message = message;

    vm.close = function () {
      $modalInstance.close();
    };

  });
