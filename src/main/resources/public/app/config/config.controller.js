'use strict';

angular.module('acts').controller('configCtrl',
    function ($scope, $uibModal, configService) {
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

        if ($scope.selected.pop() === undefined) {
          $scope.selected.push(element);
          element.selected = true;
        } else {
          element.selected = false;
        }
      }

      function deleteSelected() {
        let ids = [];

        selected.forEach(item => {
          ids.push(item.id);
        });

        configService.deleteAll(ids).then(result => {
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
          activate();
        });
      }
    });