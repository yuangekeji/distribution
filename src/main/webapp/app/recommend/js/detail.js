angular.module('recommend').controller('recommendDetailController',function (title, $scope, $http,  $state, $stateParams, $sessionStorage) {
        title.setTitle('详情信息');
        $scope.status = $stateParams.status ? $stateParams.status : '';
        $scope.param = {};
        $scope.totalPrice = 0;
        $scope.goodsList = [];
        $scope.onInit = function () {
            $http.get(ctx + '/recommend/detailInfo?id=' + $stateParams.id).success(function (resp) {
                $scope.goodsList = resp.data.goodsList;
                $scope.param = resp.data.param;
                $sessionStorage.borrowAmount = resp.data.param.borrowAmount;
                angular.forEach($scope.goodsList, function (data, index) {
                    $scope.totalPrice += data.price;
                });
                if ($scope.goodsList != null && $scope.goodsList.length > 0) {
                    angular.forEach($scope.goodsList, function (data, index) {
                        if (data.materialIds != null && data.materialIds.length > 0) {
                            angular.forEach(data.materialIds, function (data1, idx) {
                                var id = data1;
                                if (typeof data1 == 'object') {
                                    id = data1.id;
                                }
                                $http.get(ctx + '/evalute/getDownloadUrl/' + id).success(function (res) {
                                    $scope.goodsList[index].materialIds[idx] = res.data + '?v=' + new Date().getTime();
                                });
                            });
                        }
                    });
                }
            });
        }
        $scope.onInit();
        $scope.previewImage = function (url, urls) {
            Jssdk.previewImage(url, urls);
        }
        $scope.cancel = function (id) {
            $.modal({
                title: '您是否确定取消物品的预约？',
                buttons: [
                    {
                        text: '确定',
                        onClick: function () {
                            $http.get(ctx + '/recommend/cancel?pawnId=' + id).success(function (resp) {
                                if (resp.successful) {
                                    //取消预约成功
                                    $.alert('取消预约成功,正在返回推荐渠道列表', function () {
                                        $state.go('app.recommend-list');
                                    });
                                } else {
                                    $.alert(resp.errorMsg);
                                }
                            });
                        }
                    },
                    {
                        text: '取消',
                        onClick: function () {
                        }
                    },
                ]
            });

        };
        $scope.re = function (id) {
            $sessionStorage.goodsCache = $scope.goodsList;
            $sessionStorage.goodsType = $scope.param.goodsType;
            $sessionStorage.state = 'app.recommend-detail';
            $sessionStorage.again = 'again';
            $state.go('app.recommend-add', {id: id, edit: 1, pawnId: $stateParams.id});
        };
        $scope.goList = function () {
            $state.go('app.recommend-list');
        }
    });
angular.module('recommend').filter("Import",function () {
    return function (input) {
        if(input=='0'){return '国产'};
        if(input=='1'){return '进口'};
    }
});
angular.module('recommend').filter("isLoan",function () {
    return function (input) {
        if(input=='0'){return '无贷款'};
        if(input=='1'){return '有贷款'};
    }
});
angular.module('recommend').filter('ZeroFilter',function(){
   return function (input) {
       if(input==undefined||input==null||input==''){
           return '0.00';
       }else if(input.toString().split('.').length==1){
           return input.toString()+'.00';
       }else if(input.toString().split('.').length==2&&input.toString().split('.')[1].length==1){
           return input.toString()+'0';
       }else{
           return input;
       }
   }
});