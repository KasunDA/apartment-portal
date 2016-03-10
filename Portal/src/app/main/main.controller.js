(function() {
  'use strict';

  angular
    .module('portal')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($timeout, toastr) {
    var vm = this;

    function init() {
      vm.slides = [
        {
          image: "https://799a11f41b919e9ba5f4-2ba07882a6004d819cd2aaf8c0bc7cf8.ssl.cf5.rackcdn.com/09030590-rental-1doe1dm-o.jpg",
          text: "Rent",
          id: 1
        }
      ];

    }

    init();

  }
})();
