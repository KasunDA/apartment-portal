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

    this.chargeCreditCard = function (data) {
      return post("payment/chargeCreditCard", data);
    };

    this.contact = function (data) {
      return post("guest/contact", data);
    };

    this.search = function (data) {
      var queryStr = "";
      queryStr += "?noOfBedrooms=" + data.noOfBedrooms;
      queryStr += "&noOfBathrooms=" + data.noOfBathrooms;
      queryStr += "&patios=" + data.patios;
      return get("guest/search", queryStr);
    };

    this.getApartment = function (apartmentId) {
      var queryStr = "";
      queryStr += "?aptId=" + apartmentId;
      return get("guest/getApartment", queryStr);
    };

    this.bookmarkApt = function (data) {
      return post("guest/bookmarkApt", data);
    };
  }

})();
