(function () {
  'use strict';

  angular
    .module('portal')
    .run(runBlock);

  runBlock.$inject = ["$rootScope", "$log"]

  /** @ngInject */
  function runBlock($rootScope, $log) {

    $log.debug('runBlock end');

    $rootScope.global_role = "";
    //$rootScope.global_auth = false;
  }

})();
