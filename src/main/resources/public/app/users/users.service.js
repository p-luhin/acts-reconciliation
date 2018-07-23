'use strict';

angular.module('acts').factory('usersService', function ($http) {
  return {
    getAll: getAll,
    deleteAll: deleteAll
  };

  function getAll() {
    return $http.get('/api/users');
  }

  function deleteAll(ids) {
    return $http.post('/api/users/delete', ids);
  }
});