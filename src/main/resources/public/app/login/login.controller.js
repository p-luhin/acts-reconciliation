'use strict';

angular.module('acts').controller('loginCtrl',
    function ($scope, $location, loginService) {

      $scope.login = login;

      function login(username, password) {
        loginService.login(username, password).then(result => {
          toastr.success('Успешный вход в систему');
          $location.path('/config')
        });
      }
    });