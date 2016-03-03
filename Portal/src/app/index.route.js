(function() {
  'use strict';

  angular
    .module('portal')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
      .state('resident', {
        url: '/resident/home',
        templateUrl: 'app/resident/resident.html',
        controller: 'ResidentController',
        controllerAs: 'vm'
      })
      .state('guest', {
        url: '/guest/home',
        templateUrl: 'app/guest/guest/guest.html',
        controller: 'GuestController',
        controllerAs: 'vm'
      })
      .state('guest-schedule', {
        url: '/guest/schedule',
        templateUrl: 'app/guest/schedule/schedule.html',
        controller: 'GuestScheduleController',
        controllerAs: 'vm'
      })
      .state('admin-home', {
        url: '/admin/home',
        templateUrl: 'app/admin/admin-home/admin-home.html',
        controller: 'AdminHomeController',
        controllerAs: 'vm'
      })
      .state('admin-apt-list', {
        url: '/admin/apartment/list',
        templateUrl: 'app/admin/apt-list/aptlist.html',
        controller: 'AdminAptListController',
        controllerAs: 'vm'
      })
      .state('admin-apt-edit', {
        url: '/admin/apartment/edit/:apartmentId',
        templateUrl: 'app/admin/apt-list/aptlist.html',
        controller: 'AdminAptListController',
        controllerAs: 'vm'
      })
      .state('admin-lease-list', {
        url: '/admin/lease/list',
        templateUrl: 'app/admin/lease-list/lease-list.html',
        controller: 'AdminLeaseListController',
        controllerAs: 'vm'
      })
      .state('admin-referral-list', {
        url: '/admin/referral/list',
        templateUrl: 'app/admin/referral-list/referrallist.html',
        controller: 'AdminReferralListController',
        controllerAs: 'vm'
      })
      .state('admin-resident-issue-list', {
        url: '/admin/resident/issue/list',
        templateUrl: 'app/admin/resident-issues/residentissues.html',
        controller: 'AdminIssueListController',
        controllerAs: 'vm'
      })
      .state('admin-resident-list', {
        url: '/admin/resident/list',
        templateUrl: 'app/admin/residentlist.html',
        controller: 'AdminResidentListController',
        controllerAs: 'vm'
      })
      .state('admin-resident', {
        url: '/admin/resident/view/:residentId',
        templateUrl: 'app/admin/resident/resident.html',
        controller: 'AdminResidentController',
        controllerAs: 'vm'
      })
      .state('admin-guest-list', {
        url: '/admin/guest/list',
        templateUrl: 'app/admin/guest-list/guestlist.html',
        controller: 'AdminGuestListController',
        controllerAs: 'vm'
      })
      .state('admin-guest', {
        url: '/admin/guest/view/:guestId',
        templateUrl: 'app/admin/guest/guest.html',
        controller: 'AdminGuestController',
        controllerAs: 'vm'
      })
      .state('apt-search', {
        url: '/apartment/search',
        templateUrl: 'app/aptsearch/aptsearch.html',
        controller: 'AptSearchController',
        controllerAs: 'vm'
      })
      .state('apt-view', {
        url: '/apartment/view/:aptId',
        templateUrl: 'app/aptview/aptview.html',
        controller: 'AptViewController',
        controllerAs: 'vm'
      })
      .state('contact', {
        url: '/contact',
        templateUrl: 'app/contact/contact.html',
        controller: 'ContactController',
        controllerAs: 'vm'
      })
      .state('referral', {
        url: '/referral',
        templateUrl: 'app/referral/referral.html',
        controller: 'ReferralController',
        controllerAs: 'vm'
      })
      .state('maintenance', {
        url: '/maintenance',
        templateUrl: 'app/maintenance/maintenance.html',
        controller: 'MaintenanceController',
        controllerAs: 'vm'
      })
      .state('payment', {
        url: '/payment',
        templateUrl: 'app/payment/payment.html',
        controller: 'PaymentController',
        controllerAs: 'vm'
      });

    $urlRouterProvider.otherwise('/');
  }

})();
