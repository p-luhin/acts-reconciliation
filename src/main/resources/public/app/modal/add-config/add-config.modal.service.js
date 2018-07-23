'use strict';

angular.module('acts').factory('addConfigModalService', function ($http) {
  return {
    addConfig: addConfig
  };

  function addConfig(config) {
    return $http.post('/api/acts/config/', config);
  }
});