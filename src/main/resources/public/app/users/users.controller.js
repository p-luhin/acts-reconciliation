'use strict';

angular.module('acts').controller('usersCtrl',
    function ($scope, $uibModal, toastr, usersService) {
      activate();

      $scope.openModalAddUser = openModalAddUser;
      $scope.select = select;
      $scope.deleteSelected = deleteSelected;
      $scope.selected = [];

      function activate() {
        usersService.getAll().then(response => {
          $scope.users = response.data;
          $scope.selected = [];
        })
      }

      function select(index) {
        let element = $scope.users[index];
        let elementIndexInSelected = $scope.selected.indexOf(element);

        if (elementIndexInSelected < 0) {
          $scope.selected.push(element);
          element.selected = true;
        } else {
          $scope.selected.splice(elementIndexInSelected, 1);
          element.selected = false;
        }
      }

      function deleteSelected() {
        let ids = [];

        $scope.selected.forEach(item => {
          ids.push(item.id);
        });

        usersService.deleteAll(ids).then(result => {
          toastr.success('Успех');
          activate();
        })
      }

      function openModalAddUser() {
        $uibModal.open({
          animation: true,
          ariaLabelledBy: 'modal-title',
          ariaDescribedBy: 'modal-body',
          templateUrl: 'app/modal/add-user/add-user.modal.tmpl.html',
          controller: 'addUserModalCtrl',
          size: 'lg'
        }).result.then(function () {
          toastr.success('Пользователь добавлен', 'Успех');
          activate();
        });
      }
    });