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

      $('#login').keyup(event => {
        if (event.keyCode === 13) {
          if ($scope.username && $scope.password) {
            $('#loginButton').click();
          }
        }
      });

      $('#password').keyup(event => {
        if (event.keyCode === 13) {
          if ($scope.username && $scope.password) {
            $('#loginButton').click();
          }
        }
      });
    });