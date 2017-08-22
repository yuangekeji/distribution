angular.module('recommend').controller('recommendAddController',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
        title.setTitle('物品信息');
        $scope.evaluteId = $stateParams.evaluteId ? $stateParams.evaluteId : '';
        $scope.edit = $stateParams.edit ? $stateParams.edit : '';
        $scope.del = $stateParams.del ? $stateParams.del : '';
        $scope.pawnId = $stateParams.pawnId ? $stateParams.pawnId : '';
        $scope.goodsType = 3;
        if($stateParams.goodsType){
            $scope.goodsType = $stateParams.goodsType;
        } else if($sessionStorage.goodsType){
            $scope.goodsType = $sessionStorage.goodsType;
            $sessionStorage.goodsType = "";
        }
        $scope.status = '';
        $scope.goodsList = [];

        $scope.onInit = function () {
            if ($stateParams.del == 1) {
                $sessionStorage.goodsCache = [];
            }
            var defer = $q.defer();
            if ($scope.pawnId && !$sessionStorage.searchFlag) {
                $http.get(ctx + '/recommend/detailInfo?id=' + $scope.pawnId).success(function (resp) {
                    $scope.goodsList = resp.data.goodsList;
                    $sessionStorage.goodsCache = $scope.goodsList;
                    $sessionStorage.searchFlag = true;
                    defer.resolve();
                });
            } else {
                defer.resolve();
            }
            var promis = defer.promise;
                promis.then(function () {
                if ($stateParams.edit) {
                    if ($sessionStorage.goodsCache && $sessionStorage.goodsCache.length > 0){
                        $scope.goodsList = angular.copy($sessionStorage.goodsCache);
                    }
                    angular.forEach($scope.goodsList,function (data,index) {
                        if(data.goodsType=="3"){
                            angular.forEach(data.dynamics,function (dy,dindex) {
                                if(dy.value && !isNaN(Number(dy.value)) && dy.pageType=="number"){
                                    dy.value = Number(dy.value);
                                }
                            })
                        }
                    });
                    if ($scope.goodsList != null && $scope.goodsList.length > 0) {
                        angular.forEach($scope.goodsList, function (data, index) {
                            if (data.materialIds != null && data.materialIds.length > 0) {
                                angular.forEach(data.materialIds, function (dat, idx) {
                                    var id = dat;
                                    if (typeof dat == 'object') {
                                        id = dat.id;
                                    }
                                    $http.get(ctx + '/evalute/getDownloadUrl/' + id).success(function (res) {
                                        var bean = {
                                            id: id,
                                            url: res.data
                                        };
                                        $scope.goodsList[index].materialIds[idx] = bean;
                                    });
                                });
                            }
                        });
                    }
                } else {
                    $sessionStorage.goodsCache = [];
                }
            });
        };
        $scope.onInit();
        $scope.changeGoodsType = function (idx) {
            if ($scope.goodsList.length > 0 || $sessionStorage.interestRate || $sessionStorage.again)return;
            $scope.goodsType = idx
        };
        $scope.gotoAddPage2 = function () {
            if ($scope.goodsType == 1) {
                //1:房产
                $state.go('app.goods-house', {
                    goodsType: $scope.goodsType,
                    state: 'app.recommend-add',
                    jump: 'app.recommend-add',
                    pawnId: $scope.pawnId
                });
            } else if ($scope.goodsType == 2) {
                //2:汽车
                $state.go('app.goods-car', {
                    goodsType: $scope.goodsType,
                    state: 'app.recommend-add',
                    jump: 'app.recommend-add',
                    pawnId: $scope.pawnId
                });
            } else if ($scope.goodsType == 3) {
                //3:民品
                $state.go('app.goods-public', {
                    goodsType: $scope.goodsType,
                    state: 'app.recommend-add',
                    jump: 'app.recommend-add',
                    typeCode: $stateParams.typeCode,
                    pawnId: $scope.pawnId
                });
            }
        };
        $scope.gotoListPage = function () {
            $.modal({
                title: '你确定要放弃当前编辑内容吗?',
                buttons: [
                    {
                        text: '确定',
                        onClick: function () {
                            if($sessionStorage.state){
                                $state.go($sessionStorage.state,{
                                    id:$scope.pawnId
                                });
                                $sessionStorage.again = '';
                                $sessionStorage.goodsCache = null;
                                $sessionStorage.searchFlag = undefined;
                            }else{
                                $sessionStorage.goodsCache = null;
                                $state.go('app.recommend-list');
                            }
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
        $scope.submitFlag = true;
        $scope.ok = function () {
            if(!$scope.submitFlag)return;
            $scope.submitFlag = false;
            $scope.param = {
                goodsList: $scope.goodsList,
                goodsType: $scope.goodsType,
                pawnId: $scope.pawnId,
                pawnType: $scope.pawnType,
                evaluteId: $scope.evaluteId,
                edit: $scope.edit,
            };
            if($scope.goodsList){
                $sessionStorage.goodsCache = $scope.goodsList;
            }
            $state.go('app.chooseShop', {
                /*recommendId: resp.data.id,
                rebateAmountMin: resp.data.rebateAmountMin,
                rebateAmountMax: resp.data.rebateAmountMax,*/
                goodsType: $scope.goodsType,
                pawnId: $scope.pawnId,
                pawnType: $scope.pawnType,
                evaluteId: $scope.evaluteId,
                state: 'recommend'
            });
            /*$http.post(ctx + '/recommend/goodsSubmit', $scope.param).success(function (resp) {
                if (resp.successful) {
                    // $state.go('app.recommend-success', {
                    $state.go('app.chooseShop', {
                        recommendId: resp.data.id,
                        rebateAmountMin: resp.data.rebateAmountMin,
                        rebateAmountMax: resp.data.rebateAmountMax,
                        goodsType: $scope.goodsType,
                        pawnId: resp.data.id,
                        pawnType: $scope.pawnType,
                        evaluteId: $scope.evaluteId,
                        state: 'recommend'
                    });
                    $sessionStorage.recommendId = resp.data.id;
                } else {
                    $.alert(resp.errorMessage);
                }
                $scope.submitFlag = true;
            });*/
        };
        $scope.removeGoods = function (index) {
            $.modal({
                title: '是否确定删除当前当物信息？',
                buttons: [
                    {
                        text: '确定',
                        onClick: function () {
                            $scope.goodsList.splice(index, 1);
                            $sessionStorage.goodsCache.splice(index, 1);
                            $scope.$apply();
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
        $scope.editGoods = function (index) {
            $sessionStorage.goodsCache = $scope.goodsList;
            if ($scope.goodsType == 1) {
                //1:房产
                $state.go('app.goods-house', {
                    pawnType: $scope.pawnType,
                    pawnId: $scope.pawnId,
                    goodsType: $scope.goodsType,
                    index: index,
                    state: 'app.recommend-add',
                    jump: 'app.recommend-add',
                });
            } else if ($scope.goodsType == 2) {
                //2:汽车
                $state.go('app.goods-car', {
                    pawnType: $scope.pawnType,
                    pawnId: $scope.pawnId,
                    goodsType: $scope.goodsType,
                    index: index,
                    state: 'app.recommend-add',
                    jump: 'app.recommend-add',
                });
            } else if ($scope.goodsType == 3) {
                //3:民品
                $state.go('app.goods-public', {
                    pawnType: $scope.pawnType,
                    pawnId: $scope.pawnId,
                    goodsType: $scope.goodsType,
                    index: index,
                    state: 'app.recommend-add',
                    jump: 'app.recommend-add',
                    typeCode: $stateParams.typeCode
                });
            }
        };
        $scope.gotoPawn = function () {
            $state.go('app.chooseShop', {goodsType: $scope.goodsType, pawnId: $scope.pawnId, state: 'recommend'});
        };
        $scope.cancel = function () {
            $.modal({
                title: '确定要取消预约吗?',
                buttons: [
                    {
                        text: '确定',
                        onClick: function () {
                            $http.get(ctx + '/recommend/cancel?pawnId=' + $scope.pawnId).success(function (resp) {
                                if (resp.successful) {
                                    $scope.gotoListPage();
                                } else {
                                    $.alert(resp.errorMessage);
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
        }
    });