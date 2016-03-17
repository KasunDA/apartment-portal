(function () {
  'use strict';

  angular
    .module('portal')
    .run(runBlock);

  runBlock.$inject = ["$rootScope", "$log", "$localStorage"]

  /** @ngInject */
  function runBlock($rootScope, $log, $localStorage) {

    $log.debug('runBlock end');

    $rootScope.global_role = $localStorage.global_role;
    $rootScope.global_auth = $localStorage.global_auth;
  }

})();
