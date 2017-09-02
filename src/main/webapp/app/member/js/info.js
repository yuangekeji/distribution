/**
 * Created by lijingx on 8/24/2017.
 */
angular.module('member').controller('memberInfoCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
        $scope.openGraph = function () {
            $state.go("app.graph");
        }
    });