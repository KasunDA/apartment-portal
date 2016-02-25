(function () {
  'use strict';
  angular.module("portal").controller("PaymentController", PaymentController);

  PaymentController.$inject = ["dataService"];

  function PaymentController(dataService) {
    var vm = this;

    function init(){
      vm.months = [
        {text: "January", value: "01"},
        {text: "February", value: "02"},
        {text:"March", value: "03"},
        {text:"April", value: "04"},
        {text:"May", value: "05"},
        {text:"June", value: "06"},
        {text:"July", value: "07"},
        {text:"August", value: "08"},
        {text:"September", value: "09"},
        {text:"October", value: "10"},
        {text:"November", value: "11"},
        {text:"December", value: "12"}
      ];

      vm.years = [];
      for(var i=2016, year_val = 16; i<=2030; i++, year_val++){
        vm.years.push({text:i, value: year_val});
      }
    }

    function charge() {
      vm.expiryDate = vm.expiryMonth + vm.expiryYear;

      var data = {
        name: vm.customerName,
        cardNumber: vm.cardNumber,
        expirationDate: vm.expiryDate,
        securityCode: vm.securityCode,
        billAddress1: vm.address1,
        billAddress2: vm.address2,
        billCity: vm.city,
        billState: vm.state,
        billZipCode: vm.zipCode,
        amount: vm.amount
      };
      dataService.chargeCreditCard(data);
    }

    vm.charge = function () {
      charge();
    };

    init();

  }
})();
