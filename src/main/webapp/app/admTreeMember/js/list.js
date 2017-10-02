/**
 * Created by jingxin on 2017/10/1.
 */
angular.module('admTreeMember').controller('admTreeMemberListCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage,ConfirmModal,Notify) {
    title.setTitle('分支管理');
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };
    $scope.posts = [];
    $scope.levels = [];

    $scope.initDic = function () {
        $http.get(ctx + "/admMember/init").success(function (resp) {
            if(resp.successful){
                $scope.posts = resp.data.post;
                $scope.levels = resp.data.level;
            }else{
                console.log(resp);
            }
        }).error(function (resp) {
            console.log(resp);
        })
    };

    $scope.onInit = function () {

        $http.post(ctx + '/admTreeMember/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
                $scope.loadingFlag = false;
                $scope.notData = false;
                if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
            } else {
                console.log(resp.errorMessage);
            }
        });

    };

    $scope.onInit();
    $scope.initDic();
    /**查询*/
    $scope.search = function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $http.post(ctx + '/admTreeMember/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };

    /**翻页*/
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.onInit();
    };
    $scope.gotoAddPage = function () {
        $state.go("app.admTreeMemberAdd");
    };
});

angular.module('admTreeMember').filter("MemberLevelFilter",function () {
    return function (input) {
        if(input=='member_level1'){return '普卡'};
        if(input=='member_level2'){return '铜卡'};
        if(input=='member_level3'){return '银卡'};
        if(input=='member_level4'){return '金卡'};
        if(input=='member_level5'){return '白金卡'};
        if(input=='member_level6'){return '黑金卡'};
    }
});

angular.module('admTreeMember').filter("PostLevelFilter",function () {
    return function (input) {
        if(input=='post_level1'){return '普通会员'};
        if(input=='post_level2'){return '主任'};
        if(input=='post_level3'){return '经理'};
        if(input=='post_level4'){return '总监'};
        if(input=='post_level5'){return '董事'};
        if(input=='post_level6'){return '全国董事'};
    }
});