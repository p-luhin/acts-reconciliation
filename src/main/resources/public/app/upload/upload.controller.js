'use strict';

angular.module('acts').controller('uploadCtrl',
    function ($scope, $location, uploadService, toastr) {

      $scope.uploadFile = function (file) {
        uploadService.upload(file).then(
            response => {
              toastr.success('', 'Success');
              $scope.result = response.data;
            }, error => {
              toastr.error(error.data.message, 'Ошибка')
            });
      };

      $('.custom-file-input').on('change', function () {
        let fileName = $(this).val().split('\\').pop();
        $(this).next('.custom-file-label').addClass("selected").html(fileName);
      });
    });