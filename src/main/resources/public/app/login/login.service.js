'use strict';

angular.module('acts').factory('loginService', function ($http) {
  return {
    login: login
  };

  function login(username, password) {
    const data = 'j_username=' + encodeURIComponent(username) +
        '&j_password=' + encodeURIComponent(password);

    return $http.post('/api/users/login', data, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    });
  }
});