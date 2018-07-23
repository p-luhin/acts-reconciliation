'use strict';

angular.module('acts').factory('usersService', function ($rootScope, $http) {
  return {
    getAll: getAll,
    deleteAll: deleteAll,
    getCurrent: getCurrent,
    checkAuthentication: checkAuthentication
  };

  function getAll() {
    return $http.get('/api/users');
  }

  function deleteAll(ids) {
    return $http.post('/api/users/delete', ids);
  }

  function getCurrent() {
    return $http.get('/api/users/current');
  }

  function checkAuthentication() {
    getCurrent().then(result => {
      let user = result.data;

      if (!user) {
        $rootScope.authenticated = false;
        return;
      }

      $rootScope.authenticated = true;
      localStorage.setItem('user', JSON.stringify(user));
    });
  }
});