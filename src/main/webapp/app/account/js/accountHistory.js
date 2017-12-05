angular.module('account').controller('accountHistoryCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log, $uibModal, ConfirmModal,Notify) {
        title.setTitle('账户明细');

        console.info($stateParams.memberId);

        $scope.goMemberView = function () {
            $state.go('app.member.overview');
        }
    });
