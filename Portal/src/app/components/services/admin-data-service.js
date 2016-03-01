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

  }

})();
