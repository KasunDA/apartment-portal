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
        url: '/guest',
        templateUrl: 'app/guest/guest.html',
        controller: 'GuestController',
        controllerAs: 'vm'
      })
      .state('aptsearch', {
        url: '/apartment/search',
        templateUrl: 'app/aptsearch/aptsearch.html',
        controller: 'AptSearchController',
        controllerAs: 'vm'
      })
      .state('aptview', {
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
