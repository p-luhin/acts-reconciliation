'use strict';

angular.module('acts').controller('addUserModalCtrl',
    function ($scope, $uibModalInstance, toastr, addUserModalService) {
      $scope.ok = function (user) {
        if (user.password !== user.repeatPassword) {
          toastr.error('Пароли не совпадают', 'Ошибка');
          return;
        }
        addUserModalService.addUser(user);
        $uibModalInstance.close();
      };

      $scope.cancel = function () {
        $uibModalInstance.dismiss();
      }
    });