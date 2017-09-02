angular.module('member').controller('memberCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage,$rootScope) {
    //Bright Start
    title.setTitle('个人中心');
    $scope.user = $sessionStorage.currentUser;
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
            }else{
                console.log(resp);
            }
        });
    };
    $scope.onInit();
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