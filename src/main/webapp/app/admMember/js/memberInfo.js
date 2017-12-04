angular.module('admMember').controller('admMemberInfoCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log,ConfirmModal,Notify, $timeout,$window) {
    title.setTitle('会员详情');

    $scope.memberId = $stateParams.memberId;

    //定义会员信息model
    $scope.memberInfo = {};
    //定义账户信息model
    $scope.dividendMap = {};

    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }
    $scope.onInit = function () {
        $scope.startLoading();
        $http.get(ctx + '/admMember/getMemberInfo/'+$scope.memberId).success(function (resp) {
            if(resp.successful){
                $scope.MemberInfo = resp.data.member;
                $scope.stopLoading();
            }else{
                $scope.stopLoading();
                console.log(resp);
            }
        });

        $http.get(ctx + '/admMember/getMemberDividendCount/'+$scope.memberId).success(function (resp) {
            if(resp.successful){
                $scope.dividendMap =  resp.data;
                // console.info($scope.dividendMap);
            }else{
                $scope.stopLoading();
                console.log(resp);
            }
        });

        var a = $timeout(function(){
            $http.get(ctx + '/admMember/getBankName/'+$scope.memberId).success(function (resp) {
                if(resp.successful){
                    $scope.banks = resp.data.list;
                }else{
                    $scope.stopLoading();
                    console.log(resp);
                }
            });
        },2000);
    };
    $scope.onInit();

    /**管理员修改会员名字*/
    $scope.updateMemberName = function () {
        if(!$scope.MemberInfo.memberName||!$scope.MemberInfo.memberName.trim()){
            Notify.warning("请输入会员姓名。");
        }else {
            ConfirmModal.show({
                text: '您确定要修改会员姓名吗？',
                isCancel:true //false alert ,true confirm
            }).then(function (sure) {
                if (!sure) {
                    $scope.saveFlag = true;
                    return;
                }
                $scope.startLoading();
                $http.post(ctx + "/admMember/updateMember",$scope.MemberInfo).success(function (resp) {
                    if(resp.successful){
                        Notify.success("会员姓名修改成功。");
                        $scope.stopLoading();
                        // $window.location.reload();
                    }
                }).error(function (resp) {
                    console.log(resp);
                    $scope.stopLoading();
                });
            });
        }
    }

});