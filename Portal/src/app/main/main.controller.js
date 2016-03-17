(function () {
  'use strict';

  angular
    .module('portal')
    .controller('MainController', MainController);

  MainController.$inject = ["$stateParams"];

  /** @ngInject */
  function MainController($stateParams) {
    var vm = this;

    function init() {

      vm.locationId = $stateParams.locationId;

      vm.locations = [];

      vm.locations.push({
        name: "Azure Apartments",
        address1: "123 Main St",
        address2: "",
        city: "Chicago",
        state: "IL",
        zip: "60654"
      });
      //vm.locations.push({
      //  name: "Imperial Apartments",
      //  address1: "200 E Center St",
      //  address2: "",
      //  city: "Columbus",
      //  state: "OH",
      //  zip: "43085"
      //});
      //vm.locations.push({
      //  name: "Regal Residency",
      //  address1: "1200 Naperville Rd",
      //  address2: "",
      //  city: "Naperville",
      //  state: "IL",
      //  zip: "60540"
      //});

      if (vm.locationId >= 0 && vm.locationId < vm.locations.length)
        vm.location = vm.locations[vm.locationId];

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
