/**
 * Created by lijingx on 12/4/2017.
 */
angular.module('member').controller('searchValidateCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, Notify) {
        title.setTitle('查询密码校验');
        $scope.redirectUrl = $stateParams.redirectUrl;
        $scope.searchPwd = '';

        $scope.validateSearchPwd = function () {

            if(angular.isUndefined($scope.searchPwd) || $scope.searchPwd == ''){
                Notify.warning('请输入正确金额的信息。');
                return;
            }

            $http.post(ctx + '/member/searchPwdValidate', {
                searchPwd: $scope.searchPwd
            }).success(function (resp) {
                if (resp.successful) {
                    $state.go($scope.redirectUrl);
                } else {
                    Notify.warning("查询密码错误，请重新验证。");
                }
            });

        }

    });