'use strict';

angular.module('acts').controller('loginCtrl',
    function ($scope, $location, loginService, toastr,) {

      $scope.login = login;

      function login(username, password) {
        loginService.login(username, password).then(result => {
          toastr.success('Успешный вход в систему');
          $location.path('/config')
        }, error => {
          toastr.error(error.data.message, 'Ошибка')
        })
      }
    });