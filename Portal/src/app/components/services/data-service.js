(function () {
  "use strict";

  angular.module("portal").service("dataService", DataService);

  DataService.$inject = ["$http"];

  function DataService($http) {

    var endpoint = "http://localhost:8080/rent-api/";

    function get(webApiControllerMethod, queryString) {

      var qs = queryString || "";

      var promise = $http.get(endpoint + webApiControllerMethod + qs)
        .then(function (response) {
          return response.data;
        })
        .catch(function (e) {
          //errorService.logError(e);
          throw e;
        });

      return promise;
    };

    function post(webApiControllerMethod, object) {

      var promise = $http.post(endpoint + webApiControllerMethod, object)
        .then(function (response) {
          return response.data;
        })
        .catch(function (e) {
          //errorService.logError(e);
          throw e;
        });

      return promise;
    };

    this.getIssues = function () {
      return get("resident/issues");
    };
  }

})();
