'use strict';

angular.module('acts').factory('addUserModalService', function ($http) {
  return {
    addUser: addUser
  };

  function addUser(user) {
    return $http.post('/api/users/', user);
  }
});