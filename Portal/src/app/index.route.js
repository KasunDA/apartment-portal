(function () {
  'use strict';

  angular
    .module('portal')
    .config(routerConfig)
    .run(handleStateChange);

  handleStateChange.$inject = ["$rootScope", "$state", "$localStorage"];

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/shared/mainpage/mainpage.html',
        controller: 'MainPageController',
        controllerAs: 'vm'
      })
      .state('location-home', {
        url: '/location/:locationId',
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'vm'
      })
      .state('amenities', {
        url: '/amenities',
        templateUrl: 'app/amenities/amenities.html',
        controller: 'AmenitiesController',
        controllerAs: 'vm'
      })
      .state('resident', {
        url: '/resident/home',
        templateUrl: 'app/resident/resident.html',
        controller: 'ResidentController',
        controllerAs: 'vm',
        authenticate: true,
        role: "RESIDENT"
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
      .state('guest-application', {
        url: '/guest/application',
        templateUrl: 'app/guest/guest-application/guestapplication.html',
        controller: 'GuestApplicationController',
        controllerAs: 'vm',
        authenticate: true,
        role: "GUEST"
      })
      .state('login', {
        url: '/login',
        templateUrl: 'app/login/login.html',
        controller: 'LoginController',
        controllerAs: 'vm'
      })
      .state('logout', {
        url: '/logout',
        templateUrl: 'app/logout/logout.html',
        controller: 'LogoutController',
        controllerAs: 'vm'
      })
      .state('register', {
        url: '/register',
        templateUrl: 'app/register/register.html',
        controller: 'RegisterController',
        controllerAs: 'vm'
      })
      .state('admin-home', {
        url: '/admin/home',
        templateUrl: 'app/admin/admin-home/admin-home.html',
        controller: 'AdminHomeController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-apt-list', {
        url: '/admin/apartment/list',
        templateUrl: 'app/admin/apt-list/aptlist.html',
        controller: 'AdminAptListController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-apt-edit', {
        url: '/admin/apartment/edit/:apartmentId',
        templateUrl: 'app/admin/apt-edit/aptedit.html',
        controller: 'AdminAptEditController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-lease-list', {
        url: '/admin/lease/list',
        templateUrl: 'app/admin/lease-list/lease-list.html',
        controller: 'AdminLeaseListController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-referral-list', {
        url: '/admin/referral/list',
        templateUrl: 'app/admin/referral-list/referrallist.html',
        controller: 'AdminReferralListController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-resident-issue-list', {
        url: '/admin/resident/issue/list',
        templateUrl: 'app/admin/resident-issues/residentissues.html',
        controller: 'AdminIssueListController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-resident-list', {
        url: '/admin/resident/list',
        templateUrl: 'app/admin/resident-list/residentlist.html',
        controller: 'AdminResidentListController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-resident', {
        url: '/admin/resident/view/:residentId',
        templateUrl: 'app/admin/resident/resident.html',
        controller: 'AdminResidentController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-guest-list', {
        url: '/admin/guest/list',
        templateUrl: 'app/admin/guest-list/guestlist.html',
        controller: 'AdminGuestListController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-guest', {
        url: '/admin/guest/view/:guestId',
        templateUrl: 'app/admin/guest/guest.html',
        controller: 'AdminGuestController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
      })
      .state('admin-appointments', {
        url: '/admin/appointments/list',
        templateUrl: 'app/admin/appointments/appointments.html',
        controller: 'AppointmentsController',
        controllerAs: 'vm',
        authenticate: true,
        role: "ADMIN"
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
      .state('referral-list', {
        url: '/referral/list',
        templateUrl: 'app/referral-list/referral-list.html',
        controller: 'ReferralListController',
        controllerAs: 'vm',
        authenticate: true,
        role: "RESIDENT"
      })
      .state('referral', {
        url: '/refer/friend',
        templateUrl: 'app/referral/referral.html',
        controller: 'ReferralController',
        controllerAs: 'vm',
        authenticate: true,
        role: "RESIDENT"
      })
      .state('maintenance', {
        url: '/maintenance',
        templateUrl: 'app/maintenance/maintenance.html',
        controller: 'MaintenanceController',
        controllerAs: 'vm',
        authenticate: true,
        role: "RESIDENT"
      })
      .state('payment', {
        url: '/payment',
        templateUrl: 'app/payment/payment.html',
        controller: 'PaymentController',
        controllerAs: 'vm',
        authenticate: true,
        role: "RESIDENT"
      });

    $urlRouterProvider.otherwise('/');
  }

  function handleStateChange($rootScope, $state, $localStorage) {
    $rootScope.$on("$stateChangeStart", function (event, toState, toStateParams) {

      if (toState.authenticate) {

        if (!$localStorage.global_auth) {
          $localStorage.redirectState = toState.name;
          $localStorage.redirectStateParams = toStateParams;
          event.preventDefault();
          $state.go("login");
        }
        else if (toState.role !== $localStorage.global_role) {
          $localStorage.redirectState = toState.name;
          $localStorage.redirectStateParams = toStateParams;
          event.preventDefault();
          $state.go("login");
        }
      }

    });
  }

})();
