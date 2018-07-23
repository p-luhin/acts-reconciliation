'use strict';

angular.module('acts').controller('actsCtrl',
    function ($rootScope, $location, usersService) {
      $location.path('/upload');

      activate();

      function activate() {
        usersService.checkAuthentication();
      }
    });