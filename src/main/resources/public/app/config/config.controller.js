'use strict';

angular.module('acts').controller('configCtrl',
    function ($scope, $uibModal, toastr, configService) {
      activate();

      $scope.openModalAddConfig = openModalAddConfig;
      $scope.select = select;
      $scope.deleteSelected = deleteSelected;
      $scope.selected = [];

      function activate() {
        configService.getAllConfigs().then(response => {
          $scope.configs = response.data;
          $scope.selected = [];
        })
      }

      function select(index) {
        let element = $scope.configs[index];
        let elementIndexInSelected = $scope.selected.indexOf(element);

        if (elementIndexInSelected < 0) {
          $scope.selected.push(element);
          element.selected = true;
        } else {
          console.log(elementIndexInSelected);
          $scope.selected.splice(elementIndexInSelected, 1);
          element.selected = false;
        }
      }

      function deleteSelected() {
        let ids = [];

        $scope.selected.forEach(item => {
          ids.push(item.id);
        });

        configService.deleteAll(ids).then(result => {
          toastr.success('Успех');
          activate();
        })
      }

      function openModalAddConfig() {
        $uibModal.open({
          animation: true,
          ariaLabelledBy: 'modal-title',
          ariaDescribedBy: 'modal-body',
          templateUrl: 'app/modal/add-config/add-config.modal.tmpl.html',
          controller: 'addConfigModalCtrl',
          size: 'lg'
        }).result.then(function () {
          toastr.success('Конфигурация добавлена', 'Успех');
          activate();
        });
      }
    });