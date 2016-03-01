(function () {
  "use strict";

  angular.module("portal").service("dataService", DataService);

  DataService.$inject = ["$http", "baseDataService"];

  function DataService($http, baseDataService) {

    this.getIssues = function () {
      return baseDataService.get("resident/issues");
    };

    this.chargeCreditCard = function (data) {
      return baseDataService.post("payment/chargeCreditCard", data);
    };

    this.contact = function (data) {
      return baseDataService.post("guest/contact", data);
    };

    this.search = function (data) {
      var queryStr = "";
      queryStr += "?noOfBedrooms=" + data.noOfBedrooms;
      queryStr += "&noOfBathrooms=" + data.noOfBathrooms;
      queryStr += "&patios=" + data.patios;
      return baseDataService.get("guest/search", queryStr);
    };

    this.getApartment = function (apartmentId) {
      var queryStr = "";
      queryStr += "?aptId=" + apartmentId;
      return baseDataService.get("guest/getApartment", queryStr);
    };

    this.getLease = function (leaseId) {
      var queryStr = "";
      queryStr += "?leaseId=" + leaseId;
      return baseDataService.get("admin/getLease", queryStr);
    };

    this.bookmarkApt = function (data) {
      return baseDataService.post("guest/bookmarkApt", data);
    };
  }

})();
