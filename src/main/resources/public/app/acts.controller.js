'use strict';

angular.module('acts').controller('actsCtrl',
    function ($scope, $rootScope, $location, usersService) {

      $scope.getCurrentUserName = usersService.getCurrentUserName;

      $location.path('/upload');

      activate();

      function activate() {
        usersService.checkAuthentication();
      }
    });