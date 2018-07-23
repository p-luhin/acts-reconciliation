'use strict';

angular.module('acts').controller('loginCtrl',
    function ($scope, $location, loginService, usersService, toastr) {

      $scope.login = login;

      function login(username, password) {
        loginService.login(username, password).then(result => {
          toastr.success('Успешный вход в систему');
          usersService.checkAuthentication();
          $location.path('/config');
        });
      }
    });