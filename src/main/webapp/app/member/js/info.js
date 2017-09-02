/**
 * Created by lijingx on 8/24/2017.
 */
angular.module('member').controller('memberInfoCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    $scope.currentUser = $sessionStorage.currentUser;
    $scope.MemberInfo = {};

    $scope.onInit = function () {
        $http.get(ctx + '/member/getMemberInfo/'+$scope.currentUser.id).success(function (resp) {
            if(resp.successful){
                $scope.MemberInfo = resp.data.member;
                $scope.banks = resp.data.list;
            }else{
                console.log(resp);
            }
        });
    };
    $scope.onInit();




    $scope.openGraph = function () {

        $state.go("app.graph");
    }


    });