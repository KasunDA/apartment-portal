(function () {
  "use strict";

  angular.module("portal").service("adminDataService", AdminDataService);

  AdminDataService.$inject = ["$http", "baseDataService"];

  function AdminDataService($http, baseDataService) {

    this.getApartments = function (isActive) {
      isActive = isActive || "";
      var queryStr = "";
      queryStr += "?isActive=" + isActive;
      return baseDataService.get("admin/apartment/list", queryStr);
    };

    this.getApartment = function (apartmentId) {
      var queryStr = "";
      queryStr += "?apartmentId=" + apartmentId;
      return baseDataService.get("admin/apartment/view", queryStr);
    };

    this.createApartment = function (data) {
      return baseDataService.post("admin/apartment/create", data);
    };

    this.removeApartment = function (aptId) {
      var data = {
        apartmentId: aptId
      };
      return baseDataService.post("admin/apartment/remove", data);
    };

    this.getLeaseList = function () {
      return baseDataService.get("admin/lease/list");
    };

    this.getAdminReferralsList = function () {
      return baseDataService.get("admin/referral/list");
    };

    this.approveAdminReferral = function (referralId) {
      var data = {
        referralId: referralId
      };
      return baseDataService.post("admin/referral/approve", data);
    };

    this.getIssues = function () {
      return baseDataService.get("admin/issues/list");
    };

    this.getResidentList = function () {
      return baseDataService.get("admin/resident/list");
    };

    this.getAdminResident = function (residentId) {
      var queryStr = "";
      queryStr += "?residentId=" + residentId;
      return baseDataService.get("admin/resident/view", queryStr);
    };

    this.getStaffList = function () {
      return baseDataService.get("admin/staff/list");
    };

    this.updateIssue = function (data) {
      return baseDataService.post("admin/issue/save", data);
    };

    this.getGuests = function () {
      return baseDataService.get("admin/guests");
    };

    this.getAdminAvailablePropertyList = function () {
      return baseDataService.get("admin/apartment/available/list");
    };

    this.setupResidentLease = function (data) {
      return baseDataService.post("admin/resident/lease/create", data);
    };

    this.getAdminAppointments = function (requestDate) {
      requestDate = requestDate || Date.today();
      var requestDateStr = requestDate.toString("yyyy-MM-dd");
      var queryStr = "";
      queryStr += "?requestDate=" + requestDateStr;
      return baseDataService.get("admin/appointment/list", queryStr);
    };

  }

})();
