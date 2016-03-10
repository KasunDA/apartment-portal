(function () {
  "use strict";

  angular.module("portal").service("dataService", DataService);

  DataService.$inject = ["$http", "baseDataService"];

  function DataService($http, baseDataService) {

    this.validateUser = function (username, password) {
      var data = {
        "username": username,
        "password": password
      };
      return baseDataService.post("login/validate", data);
    };

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
      queryStr += "?bedrooms=" + data.bedrooms;
      queryStr += "&bathrooms=" + data.bathrooms;
      queryStr += "&garages=" + data.garages;
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

    this.getAvailableAppointmentTimes = function (requestDate) {
      requestDate = requestDate || "";
      var queryStr = "";
      queryStr += "?requestDate=" + requestDate;
      return baseDataService.get("guest/appointment/times", queryStr);
    };

    this.schedule = function (data) {
      return baseDataService.post("guest/schedule", data);
    };

    this.getReferrals = function () {
      return baseDataService.get("resident/referral/list");
    };

    this.referAFriend = function (data) {
      return baseDataService.post("resident/referral/create", data);
    };

  }

})();
