(function () {
  "use strict";

  angular.module("portal").service("baseDataService", BaseDataService);

  BaseDataService.$inject = ["$http", "errorService"];

  function BaseDataService($http, errorService) {

    var endpoint = "http://localhost:8080/rent-api/";

    this.get = function (webApiControllerMethod, queryString) {

      var qs = queryString || "";

      var promise = $http.get(endpoint + webApiControllerMethod + qs)
        .then(function (response) {
          return response.data.data;
        })
        .catch(function (e) {
          errorService.handleError(e);
          //throw e;
        });

      return promise;
    };

    this.post = function (webApiControllerMethod, object) {

      var promise = $http.post(endpoint + webApiControllerMethod, object)
        .then(function (response) {
          return response.data.data;
        })
        .catch(function (e) {
          //errorService.logError(e);
          throw e;
        });

      return promise;
    };
  }

})();
