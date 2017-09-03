angular.module('recommend').controller('recommendListCtrl', function (title, $scope, $http, $state, $sessionStorage) {
    title.setTitle('分销中心');
    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };
    $scope.onInit = function () {
        $http.post(ctx + '/member/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
                $scope.loadingFlag = false;
                $scope.notData = false;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };
    $scope.onInit();
    $scope.gotoAddPage = function () {
        $state.go("app.recommendAdd");
    };
    $scope.gotoIndex = function () {
        $state.go('app.home');
    };
    $scope.detail = function (id) {
        $state.go('app.recommendAdd', {id: id});
    };

    /**查询*/
    $scope.search = function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $http.post(ctx + '/member/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };

    /**翻页*/
    $scope.pageChangeHandler = function(num) {
        console.log('going to page ooooo ' + num);
        $scope.myPage.pageNo = num;
        $scope.onInit();
    };

});

angular.module('recommend').filter("MemberLevelFilter",function () {
    return function (input) {
        if(input=='member_level1'){return '普卡'};
        if(input=='member_level2'){return '铜卡'};
        if(input=='member_level3'){return '银卡'};
        if(input=='member_level4'){return '金卡'};
        if(input=='member_level5'){return '白金卡'};
        if(input=='member_level6'){return '黑金卡'};
    }
});

angular.module('recommend').filter("StatusFilter",function () {
    return function (input) {
        if(input=='Y'){return '已激活'};
        if(input=='N'){return '未激活'};
    }
});
