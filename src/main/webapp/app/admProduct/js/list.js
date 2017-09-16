angular.module('admProduct').controller('admProductCtrl',function ($q, title, $scope, $http,  $state, Notify, $sessionStorage) {
    title.setTitle('商品管理');

    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };

    $scope.initParams = function () {
        $http.get(ctx + "/admGoods/findGoodsTypes").success(function (resp) {
            if(resp.successful){
                $scope.goodsTypes = resp.data;
            }
        }).error(function (resp) {
            console.log(resp);
        });
    };

    $scope.initParams();

    $scope.onInit = function () {
        $http.post(ctx + '/admGoods/list', $scope.myPage).success(function (resp) {
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

    $scope.gotoAddPage = function () {
        $state.go("app.admProductAdd");
    }

    /**翻页*/
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.onInit();
    };

    /**查询*/
    $scope.search = function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $http.post(ctx + '/admGoods/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };

    /**上架下架操作*/
    $scope.handle = function (id,status) {
        $http.post(ctx + "/admGoods/handle",{id:id,status:status}).success(function (resp) {
            if(resp.successful){
                Notify.warning("操作成功。");
                $scope.search();
            }else{
                Notify.warning("操作失败。");
            }
        })
    }

    /**修改商品信息*/
    $scope.jumpAdd = function (id) {
        $state.go("app.admProductAdd",{id:id,flag:'update'});
    }
});

angular.module('admProduct').filter("GoodsTypesFilter",function () {
    return function (input) {
        if(input=='01'){return '保健品'};
    }
});

angular.module('admProduct').filter("statusFilter",function () {
    return function (input) {
        if(input=='Y'){return '上架'};
        if(input=='N'){return '下架'};
    }
});