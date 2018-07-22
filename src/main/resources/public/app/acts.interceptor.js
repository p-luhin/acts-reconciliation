'use strict';

angular
.module('acts')
.factory('httpInterceptor',
    function ($q, $location, $injector) {

      return {
        responseError: responseError
      };

      function responseError(rejection) {
        let toastr = $injector.get('toastr');
        if (rejection.status === 401) {
          toastr.error('У вас нет прав для просмотра данного ресура.',
              'Ошибка');
          $location.path('/login');
        } else {
          let body = rejection.data;
          toastr.error(body.message, 'Ошибка');
        }

        return $q.reject(rejection);
      }
    }
);