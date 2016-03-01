(function () {
  "use strict";

  angular.module("portal").service("errorService", ErrorService);

  ErrorService.$inject = ["$modal"];

  function ErrorService($modal) {

    var defaultErrorMessage = "Unexpected error has occurred";

    this.handleError = function (error) {
      //alert(error.Message);
      console.log(error);
    };

    this.showModalAlert = function (message) {

      var modalInstance = $modal.open({
        size: "sm",
        templateUrl: "app/shared/alert/alert.html",
        controller: "AlertController",
        controllerAs: 'vm',
        resolve: {
          message: function () {
            return message || defaultErrorMessage;
          }
        }
      }).result.then(function () {

      });

      return modalInstance;
    };

    this.showModalConfirm = function (message, confirmCallback, cancelCallback) {

      var modalInstance = $modal.open({
        size: "sm",
        templateUrl: "shared/confirm-modal/confirm-modal.html",
        controller: "ConfirmModalController",
        controllerAs: 'vm',
        resolve: {
          options: function () {
            return {
              message: message,
              confirmCallback: confirmCallback,
              cancelCallback: cancelCallback
            };
          }
        }
      });

      return modalInstance.result;
    };

  }

})();
