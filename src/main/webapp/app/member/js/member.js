angular.module('member').controller('memberCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage,$rootScope,Notify,ConfirmModal) {
    //Bright Start
    title.setTitle('个人中心');
    $scope.user = $sessionStorage.currentUser;
    $scope.applyFlag = true;
    if($scope.user.memberPost){
        var str = $scope.user.memberPost.toString().substr($scope.user.memberPost.toString().length-1,$scope.user.memberPost.toString().length);
        if(Number(str)&&Number(str)!=6){
             $scope.user.nextMemberPost = $scope.user.memberPost.toString().substr(0,$scope.user.memberPost.toString().length-1) + (Number(str)+1);
        }
    }
    $scope.MemberInfo = {};

    $scope.onInit = function () {
        $http.get(ctx + '/member/getMemberInfo/'+$scope.user.id).success(function (resp) {
            if(resp.successful){
                $scope.MemberInfo = resp.data.member;
                $scope.banks = resp.data.list;
                $scope.it = resp.data.it;
            }else{
                console.log(resp);
            }
        });
    };
    $scope.onInit();

    /**
     * 申请成为运营中心
     * */
    $scope.apply = function () {
        if($scope.applyFlag) {
            $scope.applyFlag = false;
            if($scope.MemberInfo.orderTotalAmount<30000){
                Notify.warning("订单金额累计达到3万可申请成为运营中心,您的订单累计金额为"+$scope.MemberInfo.orderTotalAmount);
                $scope.applyFlag = true;
            }else if($scope.MemberInfo.orderTotalAmount>=30000 && $scope.it>0){
                Notify.warning("您已提交过成为运营中心申请，请耐心等待");
                $scope.applyFlag = true;
            }else if($scope.MemberInfo.orderTotalAmount>=30000 && $scope.it==0){
                ConfirmModal.show({
                    text: '您确定要申请成为运营中心吗？',
                    isCancel:true //false alert ,true confirm
                }).then(function (sure) {
                    if (!sure) {
                        $scope.applyFlag = true;
                        return;
                    }
                    $http.post(ctx + '/member/apply', {
                        totalOrderAmount: $scope.MemberInfo.orderTotalAmount,
                        memberId: $scope.MemberInfo.id
                    }).success(function (resp) {
                        if (resp.successful) {
                            $scope.it = 1;
                            $scope.applyFlag = true;
                        } else {
                            console.log(resp);
                            $scope.applyFlag = true;
                        }
                    });
                });
            }
        }
    };
    //Bright End

    $scope.$on('$viewContentLoaded', function() {
        App.initAjax(); // initialize core components
        Layout.setAngularJsSidebarMenuActiveLink('set', $('#sidebar_menu_link_profile'), $state); // set profile link active in sidebar menu
    });

    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;

});

angular.module('member').filter("MemberLevelFilter",function () {
    return function (input) {
        if(input=='member_level1'){return '普卡 (600)'};
        if(input=='member_level2'){return '铜卡 (1800)'};
        if(input=='member_level3'){return '银卡 (3000)'};
        if(input=='member_level4'){return '金卡 (9000)'};
        if(input=='member_level5'){return '白金卡 (30000)'};
        if(input=='member_level6'){return '黑金卡 (60000)'};
    }
});

angular.module('member').filter("PostLevelFilter",function () {
    return function (input) {
        if(input=='post_level1'){return '普通会员'};
        if(input=='post_level2'){return '主任'};
        if(input=='post_level3'){return '经理'};
        if(input=='post_level4'){return '总监'};
        if(input=='post_level5'){return '董事'};
        if(input=='post_level6'){return '全国董事'};
    }
});